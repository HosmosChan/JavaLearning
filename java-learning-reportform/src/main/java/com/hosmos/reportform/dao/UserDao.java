package com.hosmos.reportform.dao;

import com.hosmos.reportform.model.User;
import org.apache.ibatis.annotations.*;

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
 * @description 用户管理Dao层
 * @date 2019年05月08日
 */
@Mapper
public interface UserDao {
    @Select("select * from sys_user where username = #{username}")
    User getUser(String username);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_user(username, password, salt, nickName, status, createTime, gmtTime) values(#{username}, #{password}, #{salt}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
    void save(User user);

    @Delete("delete from sys_user_role where userId = #{userId}")
    void deleteUserRole(Long userId);

    void saveUserRoles(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    void update(@Param("user") User user);

    @Select("select * from sys_user where id = #{id}")
    User getById(Long id);

    @Update("update sys_user set password = #{password} where id = #{id}")
    void changePassword(Long id, String password);

    Integer count(@Param("params") Map<String, Object> params);

    List<User> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    void lastLogin(String username);
}
