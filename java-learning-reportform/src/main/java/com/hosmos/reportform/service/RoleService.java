package com.hosmos.reportform.service;

import com.hosmos.reportform.dto.RoleDto;
import com.hosmos.reportform.model.Role;

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
 * @description 角色管理业务层
 * @date 2019年05月08日
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

    /**
     * 通过角色id获取角色信息
     *
     * @param id 角色id
     * @return Role 一条角色信息
     */
    Role getById(Long id);

    /**
     * 通过用户id获取角色信息列表
     *
     * @param userId 用户id
     * @return List<Role> 角色信息列表
     */
    List<Role> listByUserId(Long userId);
}
