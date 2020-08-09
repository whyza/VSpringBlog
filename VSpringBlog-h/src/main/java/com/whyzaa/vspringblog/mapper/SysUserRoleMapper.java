package com.whyzaa.vspringblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whyzaa.vspringblog.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户角色表 Mapper 接口
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    @Select("select s.role_name from sys_role as s where id in (select role_id from sys_user_role where user_id = (select id from sys_user where user_name=#{username}))")
    List<String> getRoleByUserName(@Param("username") String username);

    @Select("select role_name from sys_role where id in (select role_id from sys_user_role where user_id = (select id from sys_user where user_name=#{username}))")
    List<String> getRoleNameByUserName(@Param("username") String username);
}
