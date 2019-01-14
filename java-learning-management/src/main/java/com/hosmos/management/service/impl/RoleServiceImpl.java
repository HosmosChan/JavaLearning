package com.hosmos.management.service.impl;

import com.hosmos.management.dao.RoleDao;
import com.hosmos.management.dto.RoleDto;
import com.hosmos.management.model.Role;
import com.hosmos.management.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 角色管理实现层
 *
 * @author chenhuayang
 */
@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired(required = false)
    private RoleDao roleDao;

    @Override
    @Transactional
    public void saveRole(RoleDto roleDto) {
        Role role = roleDto;
        if (role.getId() != null) {
            // 修改
            Role r = roleDao.getRole(role.getName());
            if (r != null && !r.getId().equals(role.getId())) {
                throw new IllegalArgumentException(role.getName() + "已存在");
            }
            roleDao.update(role);
        } else {
            // 新增
            Role r = roleDao.getRole(role.getName());
            if (r != null) {
                throw new IllegalArgumentException(role.getName() + "已存在");
            }
            roleDao.save(role);
            log.debug("新增角色{}", role.getName());
        }
        saveRolePermission(role.getId(), roleDto.getPermissionIds());
    }

    private void saveRolePermission(Long roleId, List<Long> permissionIds) {
        roleDao.deleteRolePermission(roleId);
        permissionIds.remove(0L);
        if (!CollectionUtils.isEmpty(permissionIds)) {
            roleDao.saveRolePermission(roleId, permissionIds);
        }
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        roleDao.deleteRolePermission(id);
        roleDao.deleteRoleUser(id);
        roleDao.delete(id);
        log.debug("删除角色id:{}", id);
    }

    @Override
    public int count(Map<String, Object> params) {
        return roleDao.count(params);
    }

    @Override
    public List<Role> list(Map<String, Object> params, Integer offset, Integer limit) {
        return roleDao.list(params, offset, limit);
    }

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    public List<Role> listByUserId(Long userId) {
        return roleDao.listByUserId(userId);
    }
}