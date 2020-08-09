package com.whyzaa.vspringblog.controller;


import com.whyzaa.vspringblog.service.CategoryService;
import com.whyzaa.vspringblog.util.ResponseData;
import com.whyzaa.vspringblog.util.ResponseDataUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    /**
     * @description: 查询所有分类
     * @Author: whyzaa
     * @param:
     * @creatDate: 2020/8/3
     * @return: ResponseData
     */

    @Cacheable(value = "getAllCategory")
    @RequestMapping(value = "/getAllCategory", method = RequestMethod.GET)
    public ResponseData getAllCategory() {
        return ResponseDataUtil.success("",categoryService.getAllCategory());
    }

    /**
     * @description: 根据分类id查询分类名称
     * @Author: whyzaa
     * @param: 
     * @creatDate: 2020/8/3
     * @return: 
     */
    @Cacheable(value = "getCategoryNameById", key = "#categoryId")
    @RequestMapping(value = "/getCategoryNameById", method = RequestMethod.GET)
    public ResponseData getCategoryNameById(Integer categoryId) {
        return ResponseDataUtil.success("",categoryService.getCategoryNameById(categoryId));
    }

}

