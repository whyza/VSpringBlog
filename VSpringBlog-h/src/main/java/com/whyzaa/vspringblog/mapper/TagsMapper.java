package com.whyzaa.vspringblog.mapper;

import com.whyzaa.vspringblog.entity.SysTags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-26
 */
public interface TagsMapper extends BaseMapper<SysTags> {
    boolean insertTagsList(List<SysTags> sysTags);
}
