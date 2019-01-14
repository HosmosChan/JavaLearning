package com.hosmos.management.service;

import com.hosmos.management.dto.RoleDto;
import com.hosmos.management.model.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色管理业务层
 *
 * @author chenhuayang
 */
public interface RoleService {
    /**
     * 保存角色
     *
     * @param roleDto 角色信息
     */
    void saveRole(RoleDto roleDto);
    /**
     * 通过角色id删除角色信息
     *
     * @param id 角色id
     */
    void deleteRole(Long id);
    /**
     * 通过搜索参数获取角色信息列表个数，用于list方法中进行页码计数
     *
     * @param params 搜索参数(包含角色名(name = "?")以及排列顺序(orderBy = order by ?))
     * @return int 角色信息列表个数
     */
    int count(Map<String, Object> params);
    /**
     * 通过搜索参数获取角色信息列表
     *
     * @param params 搜索参数(包含角色名(name = "?")以及排列顺序(orderBy = order by ?))
     * @param offset 指定排序顺序(asc或desc)
     * @param limit  每页显示个数
     * @return List<Dict> 角色信息列表
     */
    List<Role> list(Map<String, Object> params, Integer offset, Integer limit);

    Role getById(Long id);

    List<Role> listByUserId(Long userId);
}