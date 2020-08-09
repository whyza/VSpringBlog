package com.whyzaa.vspringblog.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.whyzaa.vspringblog.constant.CommonConstant;
import com.whyzaa.vspringblog.entity.SysArticle;
import com.whyzaa.vspringblog.exception.MyException;
import com.whyzaa.vspringblog.mapper.ArticleMapper;
import com.whyzaa.vspringblog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description:
 * date: 2020/8/5 11:12
 *
 * @author whyzaa<br />
 */
@Component
@Slf4j
public class ScheduledTasks {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private ArticleMapper articleMapper;

    /**
     * 同步文章访问量
     */
    @Scheduled(cron = "0 0/10 * * * ? ")//每1分钟
    @Transactional
    public void syncPostViews() {
        log.info("开始保存点赞数 、浏览数");
        try {
            //获取这段时间的浏览数
            Map<Object, Object> viewCountItem = redisUtil.hmget(CommonConstant.ARTICLE_VIEWCOUNT_KEY);
            if (!viewCountItem.isEmpty()) {
                for (Object item : viewCountItem.keySet()) {
                    String articleKey = item.toString();
                    String[] kv = articleKey.split("_");
                    Integer articleId = Integer.parseInt(kv[1]);
                    Integer viewCount = (Integer) viewCountItem.get(articleKey);
                    //更新到数据库
                    SysArticle sysArticle = articleMapper.selectById(articleId);
                    SysArticle updateSysArticle = new SysArticle();
                    updateSysArticle.setPageView(sysArticle.getPageView() + viewCount);
                    articleMapper.update(updateSysArticle, new UpdateWrapper<SysArticle>().eq("id", articleId));
                    //同步成功删除redis里这段时间的浏览数
                    redisUtil.del(CommonConstant.ARTICLE_VIEWCOUNT_KEY);
                }
            }else {
                log.info("暂无浏览量");
            }
        } catch (Exception e) {
            log.error("文章浏览量同步失败！",e.getMessage());
            e.printStackTrace();
        }
        log.info("结束保存点赞数 、浏览数");
    }
}