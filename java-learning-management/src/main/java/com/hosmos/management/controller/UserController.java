package com.hosmos.management.controller;

import com.hosmos.management.annotation.LogAnnotation;
import com.hosmos.management.dao.UserDao;
import com.hosmos.management.dto.UserDto;
import com.hosmos.management.model.User;
import com.hosmos.management.service.UserService;
import com.hosmos.learning.common.utils.table.PageTableHandler;
import com.hosmos.learning.common.utils.table.PageTableRequest;
import com.hosmos.learning.common.utils.table.PageTableResponse;
import com.hosmos.management.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户相关接口
 */
@Api(tags = "用户")

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private UserService userService;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存用户")
    @RequiresPermissions("sys:user:add")
    public User saveUser(@RequestBody UserDto userDto) {
        User u = userService.getUser(userDto.getUsername());
        if (u != null) {
            throw new IllegalArgumentException(userDto.getUsername() + "已存在");
        }
        return userService.saveUser(userDto);
    }

    @LogAnnotation
    @PutMapping
    @ApiOperation(value = "修改用户")
    @RequiresPermissions("sys:user:add")
    public User updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @LogAnnotation
    @ApiOperation(value = "修改头像")
    @PutMapping(params = "headImgUrl")
    public void updateHeadImgUrl(String headImgUrl) {
        User user = UserUtil.getCurrentUser();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setHeadImgUrl(headImgUrl);
        userService.updateUser(userDto);
        log.debug("{}修改了头像", user.getUsername());
    }

    @LogAnnotation
    @PutMapping("/{username}")
    @ApiOperation(value = "修改密码")
    @RequiresPermissions("sys:user:password")
    public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
        userService.changePassword(username, oldPassword, newPassword);
    }

    @GetMapping
    @ApiOperation(value = "用户列表")
    @RequiresPermissions("sys:user:query")
    public PageTableResponse listUsers(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return userService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {
            @Override
            public List<User> list(PageTableRequest request) {
                List<User> list = userService.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }

    @ApiOperation(value = "当前登录用户")
    @GetMapping("/current")
    public User currentUser() {
        return UserUtil.getCurrentUser();
    }

    @ApiOperation(value = "根据用户id获取用户")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:user:query")
    public User user(@PathVariable Long id) {
        return userService.getById(id);
    }
}