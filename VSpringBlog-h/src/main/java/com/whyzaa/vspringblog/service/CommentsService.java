package com.whyzaa.vspringblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.whyzaa.vspringblog.entity.SysComments;
import com.whyzaa.vspringblog.util.ResponseData;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-23
 */
public interface CommentsService extends IService<SysComments> {
    ResponseData addComments(SysComments sysComments);

    List<SysComments> queryCommentTree(Integer articleId);

    Page<SysComments> queryAllCommentsListPage(Integer current,Integer size);
}
