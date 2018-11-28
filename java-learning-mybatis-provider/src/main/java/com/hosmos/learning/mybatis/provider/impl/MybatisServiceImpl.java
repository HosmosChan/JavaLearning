package com.hosmos.learning.mybatis.provider.impl;

import com.hosmos.learning.mybatis.model.UserInfoModel;
import com.hosmos.learning.mybatis.provider.dao.MybatisMapper;
import com.hosmos.learning.mybatis.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MybatisServiceImpl implements MybatisService {
    @Autowired
    private MybatisMapper mybatisMapper;

    @Override
    public UserInfoModel getUserInfo(String account) {
        return mybatisMapper.getUserInfo(account);
    }
}