package com.hosmos.learning.mybatis.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.hosmos.learning.mybatis.model.UserInfoModel;
import com.hosmos.learning.mybatis.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mybatis")
public class MybatisController {
    @Autowired
    private MybatisService mybatisService;

    @RequestMapping(value = "/getUserMessage", method = RequestMethod.POST)
    public JSONObject getUserMessage(@RequestParam String account) {
        JSONObject userInfo = new JSONObject();
        UserInfoModel user = mybatisService.getUserInfo(account);
        userInfo.put("id", user.getId());
        userInfo.put("account", user.getAccount());
        userInfo.put("userName", user.getUserName());
        userInfo.put("sex", user.getSex());
        userInfo.put("password", user.getPassword());
        userInfo.put("roleName", user.getRoleName());
        userInfo.put("key", user.getKey());
        return userInfo;
    }
}