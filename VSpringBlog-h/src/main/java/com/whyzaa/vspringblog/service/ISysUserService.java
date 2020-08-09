package com.whyzaa.vspringblog.service;

import com.whyzaa.vspringblog.entity.SysUser;
import com.whyzaa.vspringblog.util.ResponseData;

import java.util.Set;

public interface ISysUserService {

    SysUser getUserByName(String username);

    /**
     * 通过用户名获取用户角色集合
     *
     * @param username 用户名
     * @return 角色集合
     */
    Set<String> getUserRolesSet(String username);

    /**
     * 通过用户名获取用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    Set<String> getUserPermissionsSet(String username);


//    Set<String> getUserRoleNamesSet(String userName);
}
