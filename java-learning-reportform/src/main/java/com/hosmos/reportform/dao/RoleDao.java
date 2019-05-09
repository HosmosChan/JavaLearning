package com.hosmos.reportform.dao;

import com.hosmos.reportform.model.Role;
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
 * @description 角色管理Dao层
 * @date 2019年05月08日
 */
@Mapper
public interface RoleDao {
    @Select("select * from sys_role where name = #{name}")
    Role getRole(String name);

    @Update("update sys_role set name = #{name}, description = #{description}, gmtTime = now() where id = #{id}")
    void update(Role role);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_role(name, description, createTime, gmtTime) values(#{name}, #{description}, now(),now())")
    void save(Role role);

    @Delete("delete from sys_role_permission where roleId = #{roleId}")
    void deleteRolePermission(Long roleId);

    void saveRolePermission(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);

    @Delete("delete from sys_user_role where roleId = #{roleId}")
    void deleteRoleUser(Long roleId);

    @Delete("delete from sys_role where id = #{id}")
    void delete(Long id);

    @Select("select * from sys_role r inner join sys_user_role ru on r.id = ru.roleId where ru.userId = #{userId}")
    List<Role> listByUserId(Long userId);

    int count(@Param("params") Map<String, Object> params);

    List<Role> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from sys_role where id = #{id}")
    Role getById(Long id);
}
