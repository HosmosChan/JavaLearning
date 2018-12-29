package com.hosmos.management.dao;

import com.hosmos.management.model.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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

    void saveRolePermission(Long roleId, List<Long> permissionIds);

    @Delete("delete from sys_user_role where roleId = #{roleId}")
    void deleteRoleUser(Long roleId);

    @Delete("delete from sys_role where id = #{id}")
    void delete(Long id);

    @Select("select * from sys_role r inner join sys_user_role ru on r.id = ru.roleId where ru.userId = #{userId}")
    List<Role> listByUserId(Long userId);

    int count(Map<String, Object> params);

    List<Role> list(Map<String, Object> params, Integer offset, Integer limit);

    @Select("select * from sys_role where id = #{id}")
    Role getById(Long id);
}