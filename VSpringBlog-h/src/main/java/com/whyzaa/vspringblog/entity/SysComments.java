package com.whyzaa.vspringblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName(value = "sys_comments")
@NoArgsConstructor
@AllArgsConstructor
public class SysComments implements Serializable {

    private static final long serialVersionUID=1L;

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
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;

    /**
     * null-->一级评论  其他为评论的评论
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 用户id
     */
    @TableField("uid")
    private Integer uid;

    /**
     * 评论用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 评论用户网址
     */
    @TableField("user_address")
    private String userAddress;

    /**
     * 子评论
     */
    @TableField("user_icon")
    private String userIcon;

    /**
     * 子评论
     */
    @TableField(exist = false)
    private List<SysComments> sysCommentsChild;

    /**
     * 被评论用户名
     */
    @TableField("reply_name")
    private String replyName;

    /**
     * 评论状态
     */
    @TableField("state")
    private Integer state;
}
