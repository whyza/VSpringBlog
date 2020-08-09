package com.whyzaa.vspringblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whyzaa.vspringblog.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表 Mapper 接口
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 通过用户账号查询用户信息
     *
     * @param username
     * @return
     */
    SysUser getUserByName(@Param("username") String username);

}
