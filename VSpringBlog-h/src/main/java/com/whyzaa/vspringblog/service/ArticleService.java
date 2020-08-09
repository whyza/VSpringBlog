package com.whyzaa.vspringblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whyzaa.vspringblog.entity.SysArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.whyzaa.vspringblog.entity.vo.SysArticleVo;
import com.whyzaa.vspringblog.util.ResponseData;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-23
 */
public interface ArticleService extends IService<SysArticle> {
    SysArticle getArticleContentById(Integer articleId);

    ResponseData addArticle(SysArticle sysArticle);

    Page<SysArticle> getArticleList(Integer current, Integer size);

    Page<SysArticle> getArticleListByCategoryId(Integer current, Integer size,Integer categoryId);

    ResponseData updateArticle(SysArticle sysArticle);

    Long getArticleViewsByIdRedis(Integer articleId);

    List<SysArticle> getRecommendArticle();
}
