package com.htjn.assetManagement.controller;

import com.htjn.assetManagement.entity.ResultEnum;
import com.htjn.assetManagement.entity.Users;
import com.htjn.assetManagement.service.UserService;
import com.htjn.assetManagement.util.Result;
import com.htjn.assetManagement.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caojy on 2017/12/11.
 */

@RestController
@Api("用户相关接口")
@RequestMapping(value = "/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        Assert.notNull(userService, "UserService 不能为空!");
        this.userService = userService;
    }

    @ApiOperation(value = "添加用户")
    @PostMapping(value = "/saveUser", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result saveUser(@RequestBody Users user,
                    @RequestParam("admin") boolean admin) {

        userService.save(user, admin);
        return ResultUtil.success(ResultEnum.SUCCESS, null);
    }

    @ApiOperation(value = "更新用户")
    @PutMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody Result updateUser(@RequestBody Users user) {
        userService.update(user);
        return ResultUtil.success(ResultEnum.SUCCESS, null);
    }

    @PutMapping(value = "/changePassword")
    @ApiOperation("修改用户密码")
    public @ResponseBody Result changePassword(@RequestParam String userId,
                                               @RequestParam String oldPassword,
                                               @RequestParam String newPassword) {
        userService.changePassword(userId, oldPassword, newPassword);
        SecurityContextHolder.clearContext();
        return ResultUtil.success(ResultEnum.SUCCESS, null);
    }


    @GetMapping(value = "/getAllUsers")
    @ApiOperation(value = "获取所有用户")
    public @ResponseBody Result getAllUser() {
        return ResultUtil.success(ResultEnum.SUCCESS, userService.getAll());
    }

    @ApiOperation(value = "获取单个用户")
    @GetMapping(value = "/getOneUser")
    public @ResponseBody Result getOneUser(@RequestParam("userId") String userId) {
        return ResultUtil.success(ResultEnum.SUCCESS, userService.getOne(userId));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/deleteUser")
    public @ResponseBody Result deleteUser(@RequestParam("userId") String userId) {
        userService.remove(userId);
        return ResultUtil.success(ResultEnum.SUCCESS, null);
    }
    @ApiOperation("根据用户名查询用户")
    @GetMapping(value = "/getByUsername")
    public @ResponseBody Result getByUsername (@RequestParam("username") String username) {
        return ResultUtil.success(ResultEnum.SUCCESS, userService.getByUsername(username));
    }

    @ApiOperation("获取当前登录的用户")
    @GetMapping(value = "/getUsername")
    public @ResponseBody Map<String, Object> getUser() {
        Map<String, Object> map = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        Collection<GrantedAuthority> authority = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof String) {
                username = "";
            } else {
                UserDetails springSecurityUser = (UserDetails) principal;
                username = springSecurityUser.getUsername();
                authority = (Collection<GrantedAuthority>) springSecurityUser.getAuthorities();
            }
        }
        if (!StringUtils.isEmpty(username)) {
            map.put("username", username);
            map.put("code", 0);
            map.put("authority", authority);
            map.put("message", "用户已登录");
        } else {
            map.put("code", -1);
            map.put("message", "未登录");
        }
        return map;
    }
}
