package com.hosmos.management.service;

import com.hosmos.management.dto.UserDto;
import com.hosmos.management.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUser(String username);

    User saveUser(UserDto userDto);

    String passwordEncoder(String credentials, String salt);

    User updateUser(UserDto userDto);

    void changePassword(String username, String oldPassword, String newPassword);

    Integer count(Map<String, Object> params);

    List<User> list(Map<String, Object> params, Integer offset, Integer limit);

    User getById(Long id);
}