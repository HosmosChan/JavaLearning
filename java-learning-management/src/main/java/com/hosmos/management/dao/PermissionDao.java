package com.hosmos.management.dao;

import com.hosmos.management.model.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionDao {
    @Insert("insert into sys_permission(parentId, name, css, href, type, permission, sort) values(#{parentId}, #{name}, #{css}, #{href}, #{type}, #{permission}, #{sort})")
    void save(Permission permission);

    @Update("update sys_permission set parentId = #{parentId}, name = #{name}, css = #{css}, href = #{href}, type = #{type}, permission = #{permission}, sort = #{sort} where id = #{id}")
    void update(Permission permission);

    @Delete("delete from sys_role_permission where permissionId = #{permissionId}")
    void deleteRolePermission(Long permissionId);

    @Delete("delete from sys_permission where id = #{id}")
    void delete(Long id);

    @Delete("delete from sys_permission where parentId = #{id}")
    void deleteByParentId(Long id);

    @Select("select * from sys_permission order by sort")
    List<Permission> listAll();

    @Select("select * from sys_permission where type = 1 order by sort")
    List<Permission> listParents();

    @Select("select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_user_role ru on ru.roleId = rp.roleId where ru.userId = #{userId} order by p.sort")
    List<Permission> listByUserId(Long userId);

    @Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} order by p.sort")
    List<Permission> listByRoleId(Long roleId);

    @Select("select * from sys_permission where id = #{id}")
    Permission getById(Long id);
}