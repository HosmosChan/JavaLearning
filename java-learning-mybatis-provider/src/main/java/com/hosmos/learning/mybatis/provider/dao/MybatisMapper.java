package com.hosmos.learning.mybatis.provider.dao;

import com.hosmos.learning.mybatis.model.UserInfoModel;

public interface MybatisMapper {
    UserInfoModel getUserInfo(String account);
}
