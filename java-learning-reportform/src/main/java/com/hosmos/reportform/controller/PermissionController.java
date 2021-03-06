package com.hosmos.reportform.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hosmos.reportform.model.Permission;
import com.hosmos.reportform.model.User;
import com.hosmos.reportform.service.PermissionService;
import com.hosmos.reportform.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
 * @description 权限管理相关接口
 * @date 2019年05月08日
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "当前登录用户拥有的权限")
    @GetMapping("/current")
    public List<Permission> permissionsCurrent() {
        List<Permission> list = UserUtil.getCurrentPermissions();
        if (list == null) {
            User user = UserUtil.getCurrentUser();
            list = permissionService.listByUserId(user.getId());
            UserUtil.setPermissionSession(list);
        }
        final List<Permission> permissions = list.stream().filter(l -> l.getType().equals(1)).collect(Collectors.toList());
        List<Permission> firstLevel = permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        firstLevel.parallelStream().forEach(p -> {
            setChild(p, permissions);
        });
        return firstLevel;
    }

    /**
     * 设置子元素
     *
     * @param p
     * @param permissions
     */
    private void setChild(Permission p, List<Permission> permissions) {
        List<Permission> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
        p.setChild(child);
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                //递归设置子元素，多级菜单支持
                setChild(c, permissions);
            });
        }
    }

    /**
     * 菜单列表
     *
     * @param pId
     * @param permissionsAll
     * @param list
     */
    private void setPermissionsList(Long pId, List<Permission> permissionsAll, List<Permission> list) {
        for (Permission per : permissionsAll) {
            if (per.getParentId().equals(pId)) {
                list.add(per);
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    setPermissionsList(per.getId(), permissionsAll, list);
                }
            }
        }
    }

    @GetMapping
    @ApiOperation(value = "菜单列表")
    @RequiresPermissions("sys:menu:query")
    public List<Permission> permissionsList() {
        List<Permission> permissionsAll = permissionService.listAll();
        List<Permission> list = Lists.newArrayList();
        setPermissionsList(0L, permissionsAll, list);
        return list;
    }

    @GetMapping("/all")
    @ApiOperation(value = "所有菜单")
    @RequiresPermissions("sys:menu:query")
    public JSONArray permissionsAll() {
        List<Permission> permissionsAll = permissionService.listAll();
        JSONArray array = new JSONArray();
        setPermissionsTree(0L, permissionsAll, array);
        return array;
    }

    @GetMapping("/parents")
    @ApiOperation(value = "一级菜单")
    @RequiresPermissions("sys:menu:query")
    public List<Permission> parentMenu() {
        List<Permission> parents = permissionService.listParents();
        return parents;
    }

    /**
     * 菜单树
     *
     * @param pId
     * @param permissionsAll
     * @param array
     */
    private void setPermissionsTree(Long pId, List<Permission> permissionsAll, JSONArray array) {
        for (Permission per : permissionsAll) {
            if (per.getParentId().equals(pId)) {
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    JSONArray child = new JSONArray();
                    parent.put("child", child);
                    setPermissionsTree(per.getId(), permissionsAll, child);
                }
            }
        }
    }

    @GetMapping(params = "roleId")
    @ApiOperation(value = "根据角色id删除权限")
    @RequiresPermissions(value = {"sys:menu:query", "sys:role:query"}, logical = Logical.OR)
    public List<Permission> listByRoleId(Long roleId) {
        return permissionService.listByRoleId(roleId);
    }

    @PostMapping
    @ApiOperation(value = "保存菜单")
    @RequiresPermissions("sys:menu:add")
    public void save(@RequestBody Permission permission) {
        permissionService.save(permission);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据菜单id获取菜单")
    @RequiresPermissions("sys:menu:query")
    public Permission get(@PathVariable Long id) {
        return permissionService.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改菜单")
    @RequiresPermissions("sys:menu:add")
    public void update(@RequestBody Permission permission) {
        permissionService.update(permission);
    }

    /**
     * 校验权限
     *
     * @return
     */
    @GetMapping("/owns")
    @ApiOperation(value = "校验当前用户的权限")
    public Set<String> ownsPermission() {
        List<Permission> permissions = UserUtil.getCurrentPermissions();
        if (CollectionUtils.isEmpty(permissions)) {
            return Collections.emptySet();
        }
        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission())).map(Permission::getPermission).collect(Collectors.toSet());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除菜单")
    @RequiresPermissions(value = {"sys:menu:del"})
    public void delete(@PathVariable Long id) {
        permissionService.delete(id);
    }
}
