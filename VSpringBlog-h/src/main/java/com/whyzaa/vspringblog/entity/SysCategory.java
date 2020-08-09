package com.whyzaa.vspringblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("sys_category")
@NoArgsConstructor
@AllArgsConstructor
public class SysCategory implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 父分类名称
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * zi分类list
     */
    @TableField(exist = false)
    private List<SysCategory> sysCategoryList;

    /**
     * 分类icon
     */
    @TableField("category_icon")
    private String categoryIcon;

    /**
     * createTime
     */
    @TableField("create_time")
    private String createTime;

    /**
     * updateTime
     */
    @TableField("update_time")
    private String updateTime;
}
