package com.whyzaa.vspringblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whyzaa.vspringblog.constant.CommonConstant;
import com.whyzaa.vspringblog.entity.SysArticle;
import com.whyzaa.vspringblog.entity.SysComments;
import com.whyzaa.vspringblog.entity.SysTags;
import com.whyzaa.vspringblog.exception.MyException;
import com.whyzaa.vspringblog.mapper.ArticleMapper;
import com.whyzaa.vspringblog.mapper.CommentsMapper;
import com.whyzaa.vspringblog.mapper.TagsMapper;
import com.whyzaa.vspringblog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whyzaa.vspringblog.util.RedisUtil;
import com.whyzaa.vspringblog.util.ResponseData;
import com.whyzaa.vspringblog.util.ResponseDataUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, SysArticle> implements ArticleService {

    @Resource
    ArticleMapper articleMapper;
    @Resource
    TagsMapper tagsMapper;
    @Resource
    CommentsMapper commentsMapper;
    @Resource
    RedisUtil redisUtil;
//    @Resource
//    ArticleTagsMapper articleTagsMapper;


    @Override
    public SysArticle getArticleContentById(Integer articleId) {
        Integer articleCommentCont = commentsMapper.selectCount(new QueryWrapper<SysComments>().eq("article_id", articleId));
        SysArticle sysArticle = articleMapper.getArticleContentById(articleId);
        sysArticle.setCommentCounts(String.valueOf(articleCommentCont));
        setArticleViewsById(articleId);
        sysArticle.setPageView(sysArticle.getPageView() + getArticleViewsByIdRedis(articleId));
        return sysArticle;
    }

    /**
     * 获得文章的访问量
     *
     * @return Long
     */
    @Override
    public Long getArticleViewsByIdRedis(Integer articleId) {
        String key = CommonConstant.ARTICLE_VIEWCOUNT_CODE + articleId;
        Map<Object, Object> viewCountItem = redisUtil.hmget(CommonConstant.ARTICLE_VIEWCOUNT_KEY);
        Integer viewCount = (Integer) viewCountItem.get(key);
        return viewCount.longValue();
    }

    @Override
    public List<SysArticle> getRecommendArticle() {
        return articleMapper.getRecommendArticle();
    }

    private void setArticleViewsById(Integer articleId) {
        String key = CommonConstant.ARTICLE_VIEWCOUNT_CODE + articleId;
        Map<Object, Object> viewCountItem = redisUtil.hmget(CommonConstant.ARTICLE_VIEWCOUNT_KEY);
        if (!viewCountItem.isEmpty()) {
            if (viewCountItem.containsKey(key)) {
                redisUtil.hincr(CommonConstant.ARTICLE_VIEWCOUNT_KEY, key, 1);
            } else {
                redisUtil.hset(CommonConstant.ARTICLE_VIEWCOUNT_KEY, key, 1);
            }
        } else {
            redisUtil.hset(CommonConstant.ARTICLE_VIEWCOUNT_KEY, key, 1);
        }
    }

    @Override
    public ResponseData addArticle(SysArticle sysArticle) {
        // SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        int resultInt;
        ResponseData result = null;
        checkSysArticle(sysArticle);
        sysArticle.setCreatTime(new Date());
        try {
            resultInt = articleMapper.insert(sysArticle);
//            List<String> tags = sysArticle.getTags();
            List<SysTags> sysTagsList = sysArticle.getSysTagsList();
//            SysTags sysTags;
            for (SysTags tag : sysTagsList) {
                tag.setArticleId(sysArticle.getId());
            }
            tagsMapper.insertTagsList(sysTagsList);
//            List<SysArticleTags> sysArticleTagsList = new ArrayList<>();
//            sysTagsList.forEach(tag -> sysArticleTagsList.add(new SysArticleTags(tag.getArticleId(), tag.getId())));
//            articleTagsMapper.insertArticleTagsList(sysArticleTagsList);
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
        if (resultInt > 0) {
            SysArticle sysArticle1 = articleMapper.selectById(sysArticle.getId());
            sysArticle.setCategoryName(sysArticle1.getCategoryName());
            BeanUtils.copyProperties(sysArticle, sysArticle1);
            result = ResponseDataUtil.success("添加成功", sysArticle);
        }
        return result;
    }

    @Override
    public Page<SysArticle> getArticleList(Integer current, Integer size) {
        Page<SysArticle> sysArticlePage = new Page<>(current, size);
        return articleMapper.getArticleList(sysArticlePage);
    }

    @Override
    public Page<SysArticle> getArticleListByCategoryId(Integer current, Integer size, Integer categoryId) {
        Page<SysArticle> sysArticlePage = new Page<>(current, size);
        return articleMapper.getArticleListByCategoryId(sysArticlePage, categoryId);
    }

    @Override
    public ResponseData updateArticle(SysArticle sysArticle) {
        int resultInt;
        ResponseData result = null;
        checkSysArticle(sysArticle);
        sysArticle.setUpdateTime(new Date());
        try {
            resultInt = articleMapper.update(sysArticle, new UpdateWrapper<SysArticle>().eq("id", sysArticle.getId()));
//            List<String> tags = sysArticle.getTags();
            List<SysTags> sysTagsList = sysArticle.getSysTagsList();
//            SysTags sysTags;
            for (SysTags tag : sysTagsList) {
                tag.setArticleId(sysArticle.getId());
            }
            System.out.println(sysTagsList);
            tagsMapper.delete(new QueryWrapper<SysTags>().eq("article_id", sysArticle.getId()));
//            for (String tag : tags) {
//                sysTags = new SysTags(tag, sysArticle.getId());
//                sysTagsList.add(sysTags);
//            }
            tagsMapper.insertTagsList(sysTagsList);
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
        if (resultInt > 0) {
//            SysArticle sysArticle1 = articleMapper.getArticleContentById(sysArticle.getId());
//            sysArticle.setCategoryName(sysArticle1.getCategoryName());
//            BeanUtils.copyProperties(sysArticle,sysArticle1);
            result = ResponseDataUtil.success("更新成功", sysArticle);
        }
        return result;
    }

    private void checkSysArticle(SysArticle sysArticle) {
        if (StringUtils.isEmpty(sysArticle.getTitle())) {
            throw new MyException("文章标题不能为空");
        }
        if (StringUtils.isEmpty(sysArticle.getHtmlText()) && StringUtils.isEmpty(sysArticle.getMrdText())) {
            throw new MyException("文章内容不能为空");
        }
        if (StringUtils.isEmpty(sysArticle.getSysTagsList().size() <= 0)) {
            throw new MyException("请输入文章标签");
        }
    }
}
