package com.hosmos.learning.mybatis.service;

import com.hosmos.learning.mybatis.model.UserInfoModel;

public interface MybatisService {
    UserInfoModel getUserInfo(String account);
}
