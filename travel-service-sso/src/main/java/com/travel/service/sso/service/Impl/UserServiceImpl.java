package com.travel.service.sso.service.Impl;

import com.travel.common.domain.TravelUser;
import com.travel.common.service.Impl.BaseServiceImpl;
import com.travel.common.utils.MapperUtils;
import com.travel.service.sso.mapper.TravelUserMapper;
import com.travel.service.sso.service.UserService;
import com.travel.service.sso.service.consumer.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<TravelUser, TravelUserMapper> implements UserService<TravelUser> {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private TravelUserMapper travelUserMapper;

    @Override
    public TravelUser login(String loginCode, String plantPassword) {

        TravelUser travelUser = null;
        String json = redisService.get(loginCode);

        //缓存无数据
        if(json == null){
            Example example = new Example(TravelUser.class);
            example.createCriteria().andEqualTo("loginCode",loginCode).andEqualTo("status","0");
            travelUser = travelUserMapper.selectOneByExample(example);
            String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            if(travelUser != null && password.equals(travelUser.getPassword())){
                try {
                    redisService.put(loginCode, MapperUtils.obj2json(travelUser),60*60*24);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return travelUser;
            }else {
                return null;
            }
        }
        //缓存中有数据
        else {
            try {
                travelUser = MapperUtils.json2pojo(json, TravelUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return travelUser;
        }

    }

    @Override
    @Transactional(readOnly = false)
    public int registry(TravelUser travelUser) {

        //设置密文密码
        if(travelUser != null && travelUser.getPassword() != null){
            String password = DigestUtils.md5DigestAsHex(travelUser.getPassword().getBytes());
            travelUser.setPassword(password);
        }
        Example example = new Example(TravelUser.class);
        example.createCriteria().andEqualTo("loginCode",travelUser.getLoginCode()).andEqualTo("status","0");
        List<TravelUser> travelUsers = travelUserMapper.selectByExample(example);
        if(travelUsers != null && travelUsers.size()>0){
            return 0;
        }
        String userCode = UUID.randomUUID().toString();
        travelUser.setUserCode(userCode);
        return travelUserMapper.insert(travelUser);

    }

    @Override
    @Transactional(readOnly = false)
    public int updUser(TravelUser travelUser) {
        //更新个人信息
        Example example = new Example(TravelUser.class);
        example.createCriteria().andEqualTo("loginCode",travelUser.getLoginCode());
        return travelUserMapper.updateByExample(travelUser, example);
    }

    @Override
    public TravelUser selectOne(String loginCode){
        TravelUser travelUser = null;
        String json = redisService.get(loginCode);
        //缓存无数据
        if(json == null){
            Example example = new Example(TravelUser.class);
            example.createCriteria().andEqualTo("loginCode", loginCode);
           return travelUserMapper.selectOneByExample(example);
        }
        //缓存中有数据
        else {
            try {
                travelUser = MapperUtils.json2pojo(json, TravelUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return travelUser;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public int delUser(String loginCode){
        return travelUserMapper.updateStatus(loginCode);
    }

    @Override
    public TravelUser selectById(String userCode){
        return travelUserMapper.selectByPrimaryKey(userCode);
    }
}
