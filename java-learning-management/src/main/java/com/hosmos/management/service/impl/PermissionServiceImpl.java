package com.hosmos.management.service.impl;

import com.hosmos.management.dao.PermissionDao;
import com.hosmos.management.model.Permission;
import com.hosmos.management.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限管理实现层
 *
 * @author chenhuayang
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired(required = false)
    private PermissionDao permissionDao;

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
        log.debug("新增菜单{}", permission.getName());
    }

    @Override
    public void update(Permission permission) {
        permissionDao.update(permission);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permissionDao.deleteRolePermission(id);
        permissionDao.delete(id);
        permissionDao.deleteByParentId(id);
        log.debug("删除菜单id:{}", id);
    }

    @Override
    public List<Permission> listByUserId(Long id) {
        return permissionDao.listByUserId(id);
    }

    @Override
    public List<Permission> listAll() {
        return permissionDao.listAll();
    }

    @Override
    public List<Permission> listParents() {
        return permissionDao.listParents();
    }

    @Override
    public List<Permission> listByRoleId(Long roleId) {
        return permissionDao.listByRoleId(roleId);
    }

    @Override
    public Permission getById(Long id) {
        return permissionDao.getById(id);
    }
}