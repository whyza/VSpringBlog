package com.whyzaa.vspringblog.controller;

import com.whyzaa.vspringblog.util.ResponseData;
import com.whyzaa.vspringblog.util.ResponseDataUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequiresPermissions("user:list")
    @RequestMapping("list")
    public ResponseData userList() {
        return ResponseDataUtil.success("获取用户信息");
    }
    @RequestMapping("testException")
    public ResponseData testException() {
        int a = 10/0;
        return ResponseDataUtil.success("获取用户信息");
    }
}
