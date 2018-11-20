package com.imooc.service.impl;

import com.imooc.mapper.CommentsMapper;
import com.imooc.pojo.Comments;
import com.imooc.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;
    @Override
    public List<Comments> getAll() {
        List<Comments> comments = commentsMapper.selectAll();
        return comments;
    }
}
