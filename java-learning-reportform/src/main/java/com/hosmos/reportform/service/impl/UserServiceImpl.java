package com.hosmos.reportform.service.impl;

import com.hosmos.reportform.constants.UserConstants;
import com.hosmos.reportform.dto.UserDto;
import com.hosmos.reportform.service.UserService;
import com.hosmos.reportform.dao.UserDao;
import com.hosmos.reportform.model.User;
import com.hosmos.reportform.utils.UserUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
 * @description 用户管理实现层
 * @date 2019年05月08日
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    @Transactional
    public User saveUser(UserDto userDto) {
        userDto.setSalt(DigestUtils.md5Hex(UUID.randomUUID().toString() + System.currentTimeMillis() + UUID.randomUUID().toString()));
        userDto.setPassword(passwordEncoder(userDto.getPassword(), userDto.getSalt()));
        userDto.setStatus(User.Status.VALID);
        userDao.save(userDto);
        saveUserRoles(userDto.getId(), userDto.getRoleIds());
        log.debug("新增用户{}", userDto.getUsername());
        return userDto;
    }

    private void saveUserRoles(Long userId, List<Long> roleIds) {
        if (roleIds != null) {
            userDao.deleteUserRole(userId);
            if (!CollectionUtils.isEmpty(roleIds)) {
                userDao.saveUserRoles(userId, roleIds);
            }
        }
    }

    @Override
    public String passwordEncoder(String credentials, String salt) {
        Object object = new SimpleHash("MD5", credentials, salt, UserConstants.HASH_ITERATIONS);
        return object.toString();
    }

    @Override
    public User updateUser(UserDto userDto) {
        userDao.update(userDto);
        saveUserRoles(userDto.getId(), userDto.getRoleIds());
        updateUserSession(userDto.getId());
        return userDto;
    }

    private void updateUserSession(Long id) {
        User current = UserUtil.getCurrentUser();
        if (current.getId().equals(id)) {
            User user = userDao.getById(id);
            UserUtil.setUserSession(user);
        }
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        User u = userDao.getUser(username);
        if (u == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        if (!u.getPassword().equals(passwordEncoder(oldPassword, u.getSalt()))) {
            throw new IllegalArgumentException("密码错误");
        }
        userDao.changePassword(u.getId(), passwordEncoder(newPassword, u.getSalt()));
        log.debug("修改{}的密码", username);
    }

    @Override
    public Integer count(Map<String, Object> params) {
        return userDao.count(params);
    }

    @Override
    public List<User> list(Map<String, Object> params, Integer offset, Integer limit) {
        return userDao.list(params, offset, limit);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }
}
