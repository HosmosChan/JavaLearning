package com.hosmos.management.service;

import com.hosmos.management.dto.BeanField;
import com.hosmos.management.dto.GenerateInput;

import java.util.List;

/**
 * 代码生成业务层
 *
 * @author chenhuayang
 */
public interface GenerateService {
    /**
     * 获取数据库表信息
     *
     * @param tableName 数据库表名
     * @return List<BeanField> 数据库表信息
     */
    List<BeanField> listBeanField(String tableName);

    /**
     * 转成驼峰并大写第一个字母
     *
     * @param string 欲转换的字符串
     * @return String 转换后的字符串
     */
    String upperFirstChar(String string);

    /**
     * 根据输入内容生成代码
     *
     * @param input 输入的内容
     */
    void saveCode(GenerateInput input);
}