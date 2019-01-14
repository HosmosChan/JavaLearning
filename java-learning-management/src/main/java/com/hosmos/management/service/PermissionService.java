package com.hosmos.management.service;

import com.hosmos.management.model.Permission;

import java.util.List;

/**
 * 权限管理业务层
 *
 * @author chenhuayang
 */
public interface PermissionService {
    /**
     * 保存权限信息
     *
     * @param permission 权限信息
     */
    void save(Permission permission);

    /**
     * 更新权限信息
     *
     * @param permission 权限信息
     */
    void update(Permission permission);

    /**
     * 通过权限id删除权限信息
     *
     * @param id 权限id
     */
    void delete(Long id);

    /**
     * 通过用户id获取权限信息列表
     *
     * @param id 用户id
     * @return List<Permission> 权限信息列表
     */
    List<Permission> listByUserId(Long id);

    /**
     * 获取所有权限信息列表
     *
     * @return List<Permission> 所有权限信息列表
     */
    List<Permission> listAll();

    /**
     * 获取父类权限下的所有子权限信息列表
     *
     * @return List<Permission> 子权限信息列表
     */
    List<Permission> listParents();

    /**
     * 通过角色id获取权限信息列表
     *
     * @param roleId 角色id
     * @return List<Permission> 权限信息列表
     */
    List<Permission> listByRoleId(Long roleId);

    /**
     * 通过权限id获取权限信息
     *
     * @param id 权限id
     * @return Permission 权限信息
     */
    Permission getById(Long id);
}