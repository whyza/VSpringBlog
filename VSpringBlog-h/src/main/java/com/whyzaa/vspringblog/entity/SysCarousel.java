package com.whyzaa.vspringblog.entity;

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
@TableName("sys_carousel")
@NoArgsConstructor
@AllArgsConstructor
public class SysCarousel implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("id")
    private Integer id;

    /**
     * 轮播图文章标题
     */
    @TableField("carousel_title")
    private String carouselTitle;

    /**
     * 轮播图文章url
     */
    @TableField("article_url")
    private String articleUrl;

    /**
     * 轮播图img
     */
    @TableField("article_img")
    private String articleImg;


}
