package com.learn.springbootdemo.dao;

import com.learn.springbootdemo.dataobject.UserInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
}
