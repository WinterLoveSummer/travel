package com.travel.service.scene.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.common.domain.TravelUser;
import com.travel.common.service.Impl.BaseServiceImpl;
import com.travel.service.scene.entity.CommentsImage;
import com.travel.service.scene.entity.TravelComments;
import com.travel.service.scene.entity.TravelRecommend;
import com.travel.service.scene.entity.TravelScene;
import com.travel.service.scene.entity.dto.CommentsDto;
import com.travel.service.scene.entity.vo.CommentsVo;
import com.travel.service.scene.entity.vo.HomeScenes;
import com.travel.service.scene.mapper.CommentsImageMapper;
import com.travel.service.scene.mapper.TravelCommentsMapper;
import com.travel.service.scene.mapper.TravelRecommendMapper;
import com.travel.service.scene.mapper.TravelSceneMapper;
import com.travel.service.scene.service.SceneService;
import com.travel.service.scene.service.consumer.UserService;
import com.travel.service.scene.utils.TravelDateUtils;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.management.ValueExp;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true,isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
public class SceneServiceImpl extends BaseServiceImpl<TravelScene, TravelSceneMapper> implements SceneService<TravelScene>{

    private static final Logger logger = LoggerFactory.getLogger(SceneService.class);

    @Autowired
    private TravelSceneMapper travelSceneMapper;

    @Autowired
    private TravelCommentsMapper travelCommentsMapper;

    @Autowired
    private CommentsImageMapper commentsImageMapper;

    @Autowired
    private TravelRecommendMapper travelRecommendMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = false)
    public int insertScene(int fileDir,int pageNum,String city){
        List<TravelScene> scenes = TravelDateUtils.readFile(fileDir,pageNum,city);
        int i = travelSceneMapper.insertList(scenes);
        return i;
    }


    @Override
    public PageInfo selectScenesByCity(int pageNum, int pageSize, String city) {
        Example example = new Example(TravelScene.class);
        example.createCriteria().andEqualTo("city",city);
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(travelSceneMapper.selectByExample(example));
        return pageInfo;
    }

    @Override
    public List<HomeScenes> getHomeData() {
        List<String> citys = Arrays.asList(
                "北京","上海","杭州","香港","澳门"
        );
        List<HomeScenes> lists = new ArrayList<>();
        citys.forEach((city)->{
            Example example = new Example(TravelScene.class);
            example.createCriteria().andEqualTo("city",city);
            List<TravelScene> scenes = travelSceneMapper.selectByExample(example);
            scenes = scenes.subList(0,8);
            HomeScenes homeScenes = new HomeScenes();
            homeScenes.setCity(city);
            homeScenes.setScenes(scenes);
            lists.add(homeScenes);
        });
        return lists;
    }

    @Override
    public TravelScene getDetails(Integer id) {
        return travelSceneMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TravelScene> getSlideshow(){
        return travelSceneMapper.selectSlideshow();
    }

    @Override
    public PageInfo<CommentsVo> getComments(int pageNum,int pageSize,Integer sceneId) {
        PageHelper.startPage(pageNum,pageSize);
        List<CommentsVo> commentsVos = travelCommentsMapper.selectComments(sceneId);
        commentsVos.forEach((c)->{
            c.setTravelUser(userService.getUser(c.getUserCode()));
            c.setImageUrls(getImageUrl(c.getId()));
        });
        PageInfo<CommentsVo> pageInfo = new PageInfo<>(commentsVos);
        return pageInfo;
    }

    public List<String> getImageUrl(Integer id){
        List<String> images = new ArrayList<>();
        Example example = new Example(CommentsImage.class);
        example.createCriteria().andEqualTo("commentsId",id);
        List<CommentsImage> commentsImages = commentsImageMapper.selectByExample(example);
        commentsImages.forEach((c)->{
            images.add(c.getPhoto());
        });
        return images;
    }

    @Override
    @Transactional(readOnly = false)
    public int setComments(CommentsDto commentsDto, TravelUser travelUser) {
        TravelComments travelComments = new TravelComments();
        if(commentsDto.getContent()==null||"".equals(commentsDto.getContent())){
            return 0;
        }
        travelComments.setContent(commentsDto.getContent());
        travelComments.setSceneId(commentsDto.getSceneId());
        if(commentsDto.getGrade() != null){
            travelComments.setGrade(commentsDto.getGrade());
        }
        travelComments.setUserCode(travelUser.getUserCode());
        int insert = travelCommentsMapper.insertComments(travelComments);
        if(insert>0){
            travelSceneMapper.updatecommentCount(commentsDto.getSceneId());
        }
        List<String> images = commentsDto.getImages();
        if(images != null && images.size()>0){
            images.forEach((c)->{
                CommentsImage commentsImage = new CommentsImage();
                commentsImage.setCommentsId(travelComments.getId());
                commentsImage.setPhoto(c);
                commentsImageMapper.insert(commentsImage);
            });
        }
        //保存记录到景点推荐表
        addRecommend(commentsDto.getSceneId(), commentsDto.getGrade(), travelUser);
        return insert;
    }

    public int addRecommend(Integer sceneId,int grade,TravelUser travelUser){
        Example example = new Example(TravelRecommend.class);
        example.createCriteria().andEqualTo("sceneId",sceneId)
                .andEqualTo("loginCode",travelUser.getLoginCode());
        List<TravelRecommend> travelRecommends = travelRecommendMapper.selectByExample(example);
        //已经有信息不保存
        if(travelRecommends != null && travelRecommends.size() > 0){
            logger.info("recommend is exist");
            return -1;
        }
        TravelRecommend travelRecommend = new TravelRecommend();
        travelRecommend.setLoginCode(travelUser.getLoginCode());
        travelRecommend.setDateBirth(travelUser.getDateBirth());
        travelRecommend.setOffice(travelUser.getOffice());
        travelRecommend.setSchool(travelUser.getSchool());
        travelRecommend.setWeight(grade);
        travelRecommend.setSceneId(sceneId);
        int insert = travelRecommendMapper.insert(travelRecommend);
        return insert;
    }

    @Override
    public List<TravelScene> getScenes() {
        return travelSceneMapper.selectAll();
    }

    @Override
    public List<TravelScene> getRecommend(TravelUser travelUser) {
        List<TravelRecommend> travelRecommends = travelRecommendMapper.selectRecommend(travelUser);
        HashMap<Integer, String> map = new HashMap<>();
        //将推荐值排序
        if(travelRecommends == null || travelRecommends.size() == 0){
            return null;
        }
        for (TravelRecommend c : travelRecommends) {
            if (map.size() == 0) {
                String value = c.getWeight() + " " + "1";
                map.put(c.getSceneId(), value);
                continue;
            }
            //记录景点id是否存在map中
            boolean sign = false;
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                if (entry.getKey().equals(c.getSceneId())) {
                    String value = entry.getValue();
                    String[] strs = value.split(" ");
                    int grade = Integer.parseInt(strs[0]);
                    int num = Integer.parseInt(strs[1]);
                    grade += c.getWeight();
                    num += 1;
                    map.put(entry.getKey(), grade + " " + num);
                    sign = true;
                    break;
                }
            }
            if (sign == false) {
                String value = c.getWeight() + " " + "1";
                map.put(c.getSceneId(), value);
            }
        }
        //计算景点的权重存入新的map集合
        HashMap<Integer,Integer> recommendMap = new HashMap<>();
        for(Map.Entry<Integer,String> entry:map.entrySet()){
            String value = entry.getValue();
            String[] s = value.split(" ");
            int grade = Integer.parseInt(s[0]);
            int num = Integer.parseInt(s[1]);
            int average = grade/num;
            recommendMap.put(entry.getKey(),average);
        }
        //根据值排序
        recommendMap =   recommendMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue(), (x1, x2) -> x2, LinkedHashMap::new));
        List<TravelScene> scenes = getSceneByWeight(recommendMap);
        return scenes;
    }

    /**
     * 按照权重顺序取出推荐景点信息
     * @param map
     * @return
     */
    protected List<TravelScene> getSceneByWeight(Map<Integer,Integer> map){
        List<TravelScene> scenes = new ArrayList<>();
        map.forEach((K,V)->{
            TravelScene travelScene = travelSceneMapper.selectByPrimaryKey(K);
            scenes.add(travelScene);
        });
        return scenes;
    }
}
