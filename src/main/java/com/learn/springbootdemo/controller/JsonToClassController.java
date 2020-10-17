package com.learn.springbootdemo.controller;

import com.learn.springbootdemo.dataobject.JsonInfo;
import com.learn.springbootdemo.service.JsonToClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: Scuman
 * @Time: 2020/10/17 11:25 AM
 */
@RequestMapping(value = "/user")
@RestController
public class JsonToClassController {
    @Resource
    private JsonToClassService jsonToClassService;

    @PostMapping("/queryInfo/{id}")
    public ResponseEntity queryUserInfoById(@RequestBody JsonInfo jsonInfo) {
        return new ResponseEntity<>(jsonToClassService.parseJsonToClass(jsonInfo), HttpStatus.OK);
    }
}
