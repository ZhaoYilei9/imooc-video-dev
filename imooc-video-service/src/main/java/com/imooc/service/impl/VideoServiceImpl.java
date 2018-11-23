package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.VideosMapper;
import com.imooc.pojo.Videos;
import com.imooc.service.VideoService;
import com.imooc.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideosMapper videosMapper;

    @Override
    public PagedResult queryVideoList(int page, int pageSize, int isSaveRecord) {

        PageHelper.startPage(page,pageSize);
        List<Videos> videos = videosMapper.selectAll();
        PageInfo pageInfo = new PageInfo(videos);

        return null;
    }
}
