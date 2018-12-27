package com.hosmos.management.service;

import com.hosmos.management.model.Permission;

import java.util.List;

public interface PermissionService {
    void save(Permission permission);

    void update(Permission permission);

    void delete(Long id);

    List<Permission> listByUserId(Long id);

    List<Permission> listAll();

    List<Permission> listParents();

    List<Permission> listByRoleId(Long roleId);

    Permission getById(Long id);
}