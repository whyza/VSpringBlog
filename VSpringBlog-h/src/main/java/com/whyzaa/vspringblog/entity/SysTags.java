package com.whyzaa.vspringblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_tags")
@NoArgsConstructor
@AllArgsConstructor
public class SysTags implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("tags_name")
    private String tagsName;
    @TableField("article_id")
    private Integer articleId;

    public SysTags(String tagsName,Integer articleId) {
        this.tagsName = tagsName;
        this.articleId = articleId;
    }
}
