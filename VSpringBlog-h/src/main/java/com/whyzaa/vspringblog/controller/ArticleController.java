package com.whyzaa.vspringblog.controller;


import com.whyzaa.vspringblog.entity.SysArticle;
import com.whyzaa.vspringblog.service.ArticleService;
import com.whyzaa.vspringblog.util.ResponseData;
import com.whyzaa.vspringblog.util.ResponseDataUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/article")
public class ArticleController {

    @Resource
    ArticleService articleService;

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)

    @CacheEvict(value = "articleListPage", allEntries = true)
    @RequiresPermissions("user:add")
    public ResponseData addArticle(@RequestBody SysArticle sysArticle) {
        return articleService.addArticle(sysArticle);
    }

    @CacheEvict(value = "articleListPage", allEntries = true)
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    @RequiresPermissions("user:update")
    public ResponseData updateArticle(@RequestBody SysArticle sysArticle) {
        return articleService.updateArticle(sysArticle);
    }

    @RequestMapping(value = "/getArticleContentByArticleId", method = RequestMethod.GET)
    public ResponseData getArticleContentByArticleId(Integer articleId) {
        logger.info("获取文章内容------->");
        return ResponseDataUtil.success("", articleService.getArticleContentById(articleId));
    }

    @Cacheable(value = "articleListPage", key = "{#current,#size}")
    @RequestMapping(value = "/getArticleListPage", method = RequestMethod.GET)
    public ResponseData getArticleListPage(@RequestParam(value = "current", defaultValue = "1") Integer current, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseDataUtil.success("", articleService.getArticleList(current, size));
    }

    @Cacheable(value = "articleListByCategoryId", key = "#categoryId")
    @RequestMapping(value = "/getArticleListByCategoryId", method = RequestMethod.GET)
    public ResponseData getArticleListByCategoryId(@RequestParam(value = "current", defaultValue = "1") Integer current, @RequestParam(value = "size", defaultValue = "10") Integer size, Integer categoryId) {
        return ResponseDataUtil.success("", articleService.getArticleListByCategoryId(current, size, categoryId));
    }

    @RequestMapping(value = "/getRecommendArticle", method = RequestMethod.GET)
    public ResponseData getRecommendArticle() {
        return ResponseDataUtil.success("", articleService.getRecommendArticle());
    }
}

