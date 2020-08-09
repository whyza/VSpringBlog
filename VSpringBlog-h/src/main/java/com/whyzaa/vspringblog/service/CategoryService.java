package com.whyzaa.vspringblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whyzaa.vspringblog.entity.SysArticle;
import com.whyzaa.vspringblog.entity.SysCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-23
 */
public interface CategoryService extends IService<SysCategory> {

    List<SysCategory> getAllCategory();

    SysCategory getCategoryNameById(Integer categoryId);

}
