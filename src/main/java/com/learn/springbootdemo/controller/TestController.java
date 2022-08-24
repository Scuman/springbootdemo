package com.learn.springbootdemo.controller;

import com.learn.springbootdemo.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test/{var1}/var2")
    public ResponseEntity<String> test(@PathVariable("var1") String var1) throws BusinessException {
        if (!StringUtils.hasLength(var1)) {
            throw new BusinessException();
        }
        return ResponseEntity.ok(var1);
    }
}
