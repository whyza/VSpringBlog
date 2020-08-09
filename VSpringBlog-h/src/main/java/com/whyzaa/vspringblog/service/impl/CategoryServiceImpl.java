package com.whyzaa.vspringblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whyzaa.vspringblog.entity.SysArticle;
import com.whyzaa.vspringblog.entity.SysCategory;
import com.whyzaa.vspringblog.mapper.ArticleMapper;
import com.whyzaa.vspringblog.mapper.CategoryMapper;
import com.whyzaa.vspringblog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-23
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, SysCategory> implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<SysCategory> getAllCategory() {
        return createSysCategoryTree(categoryMapper.selectList(null),null);
    }

    @Override
    public SysCategory getCategoryNameById(Integer categoryId) {
        return categoryMapper.selectById(categoryId);
    }


    private List<SysCategory> createSysCategoryTree(List<SysCategory> sysCategoryList, Integer parentId) {
        List<SysCategory> treeList = new ArrayList<>();
        sysCategoryList.forEach(sysCategory -> {
            if (StringUtils.equals(String.valueOf(parentId), String.valueOf(sysCategory.getParentId()))) {
                sysCategory.setSysCategoryList(createSysCategoryTree(sysCategoryList, sysCategory.getId()));
                treeList.add(sysCategory);
            }
        });
        return treeList;

    }
}
