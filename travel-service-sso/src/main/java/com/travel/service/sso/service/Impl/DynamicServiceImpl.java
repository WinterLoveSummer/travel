package com.travel.service.sso.service.Impl;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import com.travel.common.domain.TravelUser;
import com.travel.service.sso.entity.DynamicImage;
import com.travel.service.sso.entity.TravelDynamic;
import com.travel.service.sso.entity.dto.DynamicReq;
import com.travel.service.sso.entity.vo.DynamicVo;
import com.travel.service.sso.mapper.DynamicImageMapper;
import com.travel.service.sso.mapper.TravelDynamicMapper;
import com.travel.service.sso.mapper.TravelUserMapper;
import com.travel.service.sso.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author lhy
 * @date 2020/5/4 13:42
 * @desc:
 */
@Service
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    private TravelDynamicMapper travelDynamicMapper;

    @Autowired
    private DynamicImageMapper dynamicImageMapper;

    @Autowired
    private TravelUserMapper travelUserMapper;

    @Override
    public int addDynamic(DynamicReq dynamicReq, String loginCode) {
        TravelDynamic travelDynamic = new TravelDynamic();
        String dynamicCode = UUID.randomUUID().toString();
        travelDynamic.setDynamicCode(dynamicCode);
        travelDynamic.setDynamicTitle(dynamicReq.getTitle());
        travelDynamic.setDynamicMain(dynamicReq.getMain());
        travelDynamic.setLoginCode(loginCode);
        travelDynamic.setCreateTime(new Date());
        travelDynamic.setStatus("0");
        int insert = travelDynamicMapper.insert(travelDynamic);
        //添加图片到数据库
        if(dynamicReq.getImages() != null && dynamicReq.getImages().size() != 0){
            for(String image : dynamicReq.getImages()){
                DynamicImage dynamicImage = new DynamicImage();
                dynamicImage.setDynamicCode(dynamicCode);
                dynamicImage.setPhoto(image);
                dynamicImageMapper.insert(dynamicImage);
            }
        }
        return insert;
    }

    @Override
    public PageInfo<DynamicVo> getDynamics(int pageNum, int pageSize,String loginCode) {
        PageHelper.startPage(pageNum,pageSize);
        List<DynamicVo> dynamicVos = travelDynamicMapper.selectDynamics(loginCode);
        dynamicVos.forEach((c)->{
            Example example = new Example(TravelUser.class);
            example.createCriteria().andEqualTo("loginCode",c.getLoginCode());
            TravelUser travelUser = travelUserMapper.selectOneByExample(example);
            c.setTravelUser(travelUser);
            c.setDynamicImages(getDynamicImages(c.getDynamicCode()));
        });
        PageInfo<DynamicVo> pageInfo = new PageInfo<>(dynamicVos);
        return pageInfo;
    }

    public List<String> getDynamicImages(String dynamicCode){
        List<String> images = new ArrayList<>();
        Example example = new Example(DynamicImage.class);
        example.createCriteria().andEqualTo("dynamicCode",dynamicCode);
        List<DynamicImage> dynamicImages = dynamicImageMapper.selectByExample(example);
        dynamicImages.forEach(c->{
            images.add(c.getPhoto());
        });
        return images;
    }
}
