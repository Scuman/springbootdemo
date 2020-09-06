package com.learn.springbootdemo.controller;

import com.learn.springbootdemo.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/user")
@RestController
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/queryInfo/{id}")
    public ResponseEntity queryUserInfoById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userInfoService.queryUserInfoById(id), HttpStatus.OK);
    }
}
