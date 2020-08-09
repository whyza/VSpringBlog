package com.whyzaa.vspringblog.service.impl;

import com.whyzaa.vspringblog.entity.Notice;
import com.whyzaa.vspringblog.mapper.NoticeMapper;
import com.whyzaa.vspringblog.service.NoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whyzaa
 * @since 2020-08-06
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}
