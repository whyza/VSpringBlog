package com.whyzaa.vspringblog.service.impl;

import com.whyzaa.vspringblog.entity.SysTags;
import com.whyzaa.vspringblog.mapper.TagsMapper;
import com.whyzaa.vspringblog.service.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whyzaa
 * @since 2020-07-26
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, SysTags> implements TagsService {

}
