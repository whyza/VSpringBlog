package com.whyzaa.vspringblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whyzaa.vspringblog.entity.SysArticle;
import com.whyzaa.vspringblog.entity.SysComments;
import com.whyzaa.vspringblog.exception.MyException;
import com.whyzaa.vspringblog.mapper.CommentsMapper;
import com.whyzaa.vspringblog.service.CommentsService;
import com.whyzaa.vspringblog.util.ResponseData;
import com.whyzaa.vspringblog.util.ResponseDataUtil;
import org.springframework.beans.BeanUtils;
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
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, SysComments> implements CommentsService {

    @Resource
    CommentsMapper commentsMapper;

    @Override
    public ResponseData addComments(SysComments sysComments) {
        int insert;
        try {
            insert = commentsMapper.insert(sysComments);
        } catch (Exception e) {
            throw new MyException(500, e.getMessage());
        }
        if (insert > 0) {
            return ResponseDataUtil.success("评论成功");
        } else {
            return ResponseDataUtil.success("系统错误评论失败");
        }
    }

    @Override
    public List<SysComments> queryCommentTree(Integer articleId) {
        return eachComment(commentsMapper.findAllBigComments(articleId));
    }

    @Override
    public Page<SysComments> queryAllCommentsListPage(Integer current, Integer size) {
        Page<SysComments> sysComments = new Page<>(current, size);
        return (Page<SysComments>) commentsMapper.selectPage(sysComments,null);
    }

    /**
     * 循环每个顶级的评论节点
     *
     * @param comments
     * @return
     */
    private List<SysComments> eachComment(List<SysComments> comments) {
        List<SysComments> commentsView = new ArrayList<>();
        for (SysComments comment : comments) {
            SysComments c = new SysComments();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }
    private void combineChildren(List<SysComments> comments) {
        for (SysComments comment : comments) {
            List<SysComments> replys1 = commentsMapper.findCommentsByParentId(comment.getId());
            for (SysComments reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setSysCommentsChild(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }
    private List<SysComments> tempReplys = new ArrayList<>();

    /**
     * 递归迭代，剥洋葱
     *
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(SysComments comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        List<SysComments> commentsByParentId = commentsMapper.findCommentsByParentId(comment.getId());
        if (commentsByParentId.size() > 0) {
            for (SysComments reply : commentsByParentId) {
                tempReplys.add(reply);
                List<SysComments> commentsByParentId1 = commentsMapper.findCommentsByParentId(reply.getId());
                if (commentsByParentId1.size() > 0) {
                    recursively(reply);
                }
            }
        }
    }

}
