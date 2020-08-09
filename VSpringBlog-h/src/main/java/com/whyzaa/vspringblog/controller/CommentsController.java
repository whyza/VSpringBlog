package com.whyzaa.vspringblog.controller;


import com.whyzaa.vspringblog.entity.SysComments;
import com.whyzaa.vspringblog.requestLimit.LimitKey;
import com.whyzaa.vspringblog.service.CommentsService;
import com.whyzaa.vspringblog.util.ResponseData;
import com.whyzaa.vspringblog.util.ResponseDataUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Resource
    CommentsService CommentsService;

    @Cacheable(value = "queryComments", key = "#articleId")
    @RequestMapping(value = "/queryComments", method = RequestMethod.GET)
    public ResponseData queryComments(Integer articleId) {
        return ResponseDataUtil.success("", CommentsService.queryCommentTree(articleId));
    }

    @LimitKey(limit = 3)
    @CacheEvict(value = "queryComments", key = "#sysComments.getArticleId()")
    @RequestMapping(value = "/addComments", method = RequestMethod.POST)
    public ResponseData addComments(@RequestBody SysComments sysComments) {
        return CommentsService.addComments(sysComments);
    }

//    @Cacheable(value = "queryAllCommentsListPage", key = "{#current,#size}")
    @RequestMapping(value = "/queryAllCommentsListPage", method = RequestMethod.GET)
    public ResponseData queryAllCommentsListPage(@RequestParam(value = "current", defaultValue = "1") Integer current, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseDataUtil.success("", CommentsService.queryAllCommentsListPage(current, size));
    }
}

