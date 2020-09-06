package com.learn.springbootdemo.service;

import com.learn.springbootdemo.dao.UserInfoMapper;
import com.learn.springbootdemo.dataobject.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    public UserInfo queryUserInfoById(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
}
