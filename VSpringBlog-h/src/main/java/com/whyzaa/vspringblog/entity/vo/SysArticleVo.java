package com.whyzaa.vspringblog.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.whyzaa.vspringblog.entity.SysTags;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * date: 2020/7/24 17:26
 *
 * @author whyzaa<br />
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SysArticleVo implements Serializable {
    /**
     * 文章id
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容MarkDown
     */
    private String mrdText;

    /**
     * 文章内容Html
     */
    private String htmlText;

    /**
     * 用户Id
     */
    private Integer uid;

    /**
     * 发布时间
     */
    private Date creatTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态 0 -- 已发布 1-- 草稿箱 2--禁显示
     */
    private Integer state;

    private String pageView;

    /**
     * 文章标签
     */
    private List<SysTags> SysTagsList;

    private Integer categoryId;
    private String categoryName;
    /**
     * userName
     */
    private String userName;

    /**
     * 文章缩略图
     */
    private String articleThumbnail;
}
