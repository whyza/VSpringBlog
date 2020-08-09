package com.whyzaa.vspringblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_article")
@NoArgsConstructor
@AllArgsConstructor
public class SysArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    @TableId("id")
    private Integer id;

    /**
     * 文章标题
     */
    @TableField("title")
    private String title;

    /**
     * 文章内容MarkDown
     */
    @TableField("mrd_text")
    private String mrdText;

    /**
     * 文章内容Html
     */
    @TableField("html_text")
    private String htmlText;

    /**
     * 用户Id
     */
    @TableField("uid")
    private Integer uid;

    /**
     * 发布时间
     */
    @TableField("creat_time")
    private Date creatTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 状态 0 -- 已发布 1-- 草稿箱 2--禁显示
     */
    @TableField("state")
    private Integer state;

    @TableField("page_view")
    private Long pageView;

//    @TableField(exist = false)
//    private List<String> tags;

    @TableField("category_id")
    private Integer categoryId;

    @TableField("article_thumbnail")
    private String articleThumbnail;

    @TableField(exist = false)
    private String categoryName;

    /**
     * 文章标签
     */
    @TableField(exist = false)
    private List<SysTags> SysTagsList;

    /**
     * userName
     */
    @TableField(exist = false)
    private String userName;
    /**
     * commentCounts
     */
    @TableField(exist = false)
    private String commentCounts;

}
