package com.hosmos.management.service;

import com.hosmos.management.dto.RoleDto;
import com.hosmos.management.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    void saveRole(RoleDto roleDto);

    void deleteRole(Long id);

    int count(Map<String, Object> params);

    List<Role> list(Map<String, Object> params, Integer offset, Integer limit);

    Role getById(Long id);

    List<Role> listByUserId(Long userId);
}