package com.whyzaa.vspringblog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whyzaa.vspringblog.entity.SysComments;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-23
 */
public interface CommentsMapper extends BaseMapper<SysComments> {
    List<SysComments> findCommentByArticleId(Integer articleId);
    List<SysComments> findAllBigComments(Integer articleId);
    List<SysComments> findCommentsByParentId(Integer parentId);

}
