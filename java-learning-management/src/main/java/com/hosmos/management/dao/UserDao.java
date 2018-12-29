package com.hosmos.management.dao;

import com.hosmos.management.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    @Select("select * from sys_user where username = #{username}")
    User getUser(String username);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_user(username, password, salt, nickName, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, gmtTime) values(#{username}, #{password}, #{salt}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
    void save(User user);

    @Delete("delete from sys_user_role where userId = #{userId}")
    void deleteUserRole(Long userId);

    void saveUserRoles(Long userId, List<Long> roleIds);

    void update(User user);

    @Select("select * from sys_user where id = #{id}")
    User getById(Long id);

    @Update("update sys_user set password = #{password} where id = #{id}")
    void changePassword(Long id, String password);

    Integer count(Map<String, Object> params);

    List<User> list(Map<String, Object> params, Integer offset, Integer limit);
}