package com.hosmos.reportform.service;

import com.hosmos.reportform.model.Permission;

import java.util.List;

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
 * @description 权限管理业务层
 * @date 2019年05月08日
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
