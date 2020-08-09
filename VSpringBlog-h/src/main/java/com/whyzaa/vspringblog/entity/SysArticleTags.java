package com.whyzaa.vspringblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

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
@TableName("sys_article_tags")
@NoArgsConstructor
@AllArgsConstructor
public class SysArticleTags implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 文章id
     */
    @TableField("article_id")
    private Integer articleId;

    /**
     * 标签id
     */
    @TableField("tags_id")
    private Integer tagsId;

    /**
     * 标签名称
     */
    @TableField("tags_name")
    private String tagsName;

    public SysArticleTags(Integer articleId, Integer tagsId) {
        this.articleId = articleId;
        this.tagsId = tagsId;
    }

}
