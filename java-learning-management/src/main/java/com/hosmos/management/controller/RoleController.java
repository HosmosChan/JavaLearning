package com.hosmos.management.controller;

import com.google.common.collect.Maps;
import com.hosmos.learning.common.utils.table.*;
import com.hosmos.management.annotation.LogAnnotation;
import com.hosmos.management.dto.RoleDto;
import com.hosmos.management.model.Role;
import com.hosmos.management.service.RoleService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理相关接口
 *
 * @author chenhuayang
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存角色")
    @RequiresPermissions("sys:role:add")
    public void saveRole(@RequestBody RoleDto roleDto) {
        roleService.saveRole(roleDto);
    }

    @GetMapping
    @ApiOperation(value = "角色列表")
    @RequiresPermissions("sys:role:query")
    public PageTableResponse listRoles(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return roleService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<Role> list(PageTableRequest request) {
                List<Role> list = roleService.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取角色")
    @RequiresPermissions("sys:role:query")
    public Role get(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @GetMapping("/all")
    @ApiOperation(value = "所有角色")
    @RequiresPermissions(value = {"sys:user:query", "sys:role:query"}, logical = Logical.OR)
    public List<Role> roles() {
        return roleService.list(Maps.newHashMap(), null, null);
    }

    @GetMapping(params = "userId")
    @ApiOperation(value = "根据用户id获取拥有的角色")
    @RequiresPermissions(value = {"sys:user:query", "sys:role:query"}, logical = Logical.OR)
    public List<Role> roles(Long userId) {
        return roleService.listByUserId(userId);
    }

    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色")
    @RequiresPermissions(value = {"sys:role:del"})
    public void delete(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}