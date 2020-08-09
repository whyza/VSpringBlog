package com.whyzaa.vspringblog.mapper;

import com.whyzaa.vspringblog.entity.SysArticleTags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-23
 */
public interface ArticleTagsMapper extends BaseMapper<SysArticleTags> {
    boolean insertArticleTagsList(List<SysArticleTags> sysArticleTags);
}
