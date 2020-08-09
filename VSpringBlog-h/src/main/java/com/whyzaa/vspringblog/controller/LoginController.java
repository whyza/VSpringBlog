package com.whyzaa.vspringblog.controller;


import com.alibaba.fastjson.JSONObject;
import com.whyzaa.vspringblog.constant.CommonConstant;
import com.whyzaa.vspringblog.entity.SysUser;
import com.whyzaa.vspringblog.service.ISysUserService;
import com.whyzaa.vspringblog.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping("/sys")
@Slf4j
public class LoginController {
    @Resource
    ISysUserService sysUserService;
    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(@RequestBody SysUser loginUser) {
        String username = loginUser.getUserName();
        String password = loginUser.getPassWord();

        //1. 校验用户是否有效
        SysUser sysUser = sysUserService.getUserByName(username);
        if (sysUser == null) {
            return ResponseDataUtil.failure("该用户不存在，请注册");
        }
        //情况2：根据用户信息查询，该用户已注销
        if (CommonConstant.DEL_FLAG_1.toString().equals(sysUser.getDelFlag())) {
            return ResponseDataUtil.failure("该用户已注销");
        }
        String userpassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
        String syspassword = sysUser.getPassWord();
        if (!syspassword.equals(userpassword)) {
            return ResponseDataUtil.failure("用户名或密码错误");
        }
        return userInfo(sysUser);
    }


    /**
     * 用户信息
     *
     * @param sysUser
     * @return
     */
    private ResponseData userInfo(SysUser sysUser) {
        String syspassword = sysUser.getPassWord();
        String username = sysUser.getUserName();
        // 生成token
        String token = JwtUtil.sign(username, syspassword);
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        // 设置超时时间
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);

        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("userInfo", sysUser);
        return ResponseDataUtil.success("登录成功", obj);
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public ResponseData logout(HttpServletRequest request) {
        //用户退出逻辑
        String token = request.getHeader(CommonConstant.ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)) {
            return ResponseDataUtil.failure("退出登录失败！");
        }
        String username = JwtUtil.getUsername(token);
        SysUser sysUser = sysUserService.getUserByName(username);
        if (sysUser != null) {
            log.info(" 用户名:  " + sysUser.getRealName() + ",退出成功！ ");
            //清空用户Token缓存
            redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + token);
            //清空用户权限缓存：权限Perms和角色集合
            redisUtil.del(CommonConstant.LOGIN_USER_CACHERULES_ROLE + username);
            redisUtil.del(CommonConstant.LOGIN_USER_CACHERULES_PERMISSION + username);
            return ResponseDataUtil.success("退出登录成功！");
        } else {
            return ResponseDataUtil.failure("无效的token");
        }
    }

    @Cacheable(value = "userInfo",key = "#userName")
    @RequestMapping(value = "/getUsreInfo")
    public ResponseData getUsreInfo(String userName) {
        Set<String> userRolesSet = sysUserService.getUserRolesSet(userName);
        return ResponseDataUtil.success("", userRolesSet);
    }
}
