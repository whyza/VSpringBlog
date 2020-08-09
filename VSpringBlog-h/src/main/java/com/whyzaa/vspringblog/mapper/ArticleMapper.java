package com.whyzaa.vspringblog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whyzaa.vspringblog.entity.SysArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whyzaa.vspringblog.entity.vo.SysArticleVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-23
 */
public interface ArticleMapper extends BaseMapper<SysArticle> {

    SysArticle getArticleContentById(Integer articleId);

    Page<SysArticle> getArticleList(IPage page);

    Page<SysArticle> getArticleListByCategoryId(IPage page,Integer categoryId);
    @Select("SELECT s.*,st.tags_name,sc.category_name FROM sys_article as s LEFT JOIN sys_tags as st ON st.article_id = s.id LEFT JOIN sys_category as sc ON sc.id = s.category_id ORDER BY RAND() LIMIT 0,5")
    List<SysArticle> getRecommendArticle();
}
