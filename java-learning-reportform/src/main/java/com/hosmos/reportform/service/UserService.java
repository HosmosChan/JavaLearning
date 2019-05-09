package com.hosmos.reportform.service;

import com.hosmos.reportform.dto.UserDto;
import com.hosmos.reportform.model.User;

import java.util.List;
import java.util.Map;

/**
 * Code is far away from bug with the animal protected
 * 　┏┓　　  ┏┓
 * ┏┻┻━━━┻┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┣┛
 * 　　┗┳┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author chenhuayang
 * @description 用户管理业务层
 * @date 2019年05月08日
 */
public interface UserService {
    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return User 用户信息
     */
    User getUser(String username);

    /**
     * 保存用户信息
     *
     * @param userDto 用户信息
     * @return User 用户信息
     */
    User saveUser(UserDto userDto);

    /**
     * 用户密码签名
     *
     * @param credentials 密码
     * @param salt        签名密钥
     * @return String 签名后字符串
     */
    String passwordEncoder(String credentials, String salt);

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     * @return User 用户信息
     */
    User updateUser(UserDto userDto);

    /**
     * 更改密码
     *
     * @param username    用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void changePassword(String username, String oldPassword, String newPassword);

    /**
     * 通过搜索参数获取用户信息列表个数，用于list方法中进行页码计数
     *
     * @param params 搜索参数(包含用户名(username = "?")、昵称(nickName = "?")、状态(status = ?)以及排列顺序(orderBy = order by ?))
     * @return Integer 用户信息列表个数
     */
    Integer count(Map<String, Object> params);

    /**
     * 通过搜索参数获取用户信息列表
     *
     * @param params 搜索参数(包含用户名(username = "?")、昵称(nickName = "?")、状态(status = ?)以及排列顺序(orderBy = order by ?))
     * @param offset 指定排序顺序(asc或desc)
     * @param limit  每页显示个数
     * @return List<User> 用户信息列表
     */
    List<User> list(Map<String, Object> params, Integer offset, Integer limit);

    /**
     * 通过用户id获取用户信息
     *
     * @param id 权限id
     * @return User 用户信息
     */
    User getById(Long id);
}
