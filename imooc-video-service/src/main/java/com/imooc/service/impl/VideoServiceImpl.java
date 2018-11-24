package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.VideosMapper;
import com.imooc.pojo.Videos;
import com.imooc.service.VideoService;
import com.imooc.utils.PagedResult;

import com.imooc.vo.VideosVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideosMapper videosMapper;


    @Override
    public PagedResult showAll(int page, int pageSize, int isSaveRecord, Videos videos) {
        PageHelper.startPage(page,pageSize);
        List<VideosVo> videosVos = new ArrayList<>();
        String videoDesc = videos.getVideoDesc();
        videosVos = videosMapper.showAll(videoDesc);
        PageInfo pageInfo = new PageInfo(videosVos);
        PagedResult result = new PagedResult();
        result.setRows(videosVos);
        result.setTotal(pageInfo.getPages());
        result.setPage(pageInfo.getPageNum());
        result.setRecords(pageInfo.getTotal());

        return result;
    }

    @Override
    public PagedResult queryVideoList(int page, int pageSize, Videos video) {

        PageHelper.startPage(page,pageSize);
        Example example = new Example(Videos.class);
        Example.Criteria criteria = example.createCriteria();
        List<Videos> videos = new ArrayList<>();
        String userId = video.getUserId();
        criteria.andEqualTo("userId",userId);
        videos = videosMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(videos);
        PagedResult result = new PagedResult();
        result.setRows(videos);
        result.setTotal(pageInfo.getPages());
        result.setPage(pageInfo.getPageNum());
        result.setRecords(pageInfo.getTotal());
        return result;
    }

    @Override
    public void saveVideo(Videos videos) {
        videosMapper.insertSelective(videos);
    }

    @Override
    public PagedResult showMyLike(int page, int pageSize, Videos video) {
        PageHelper.startPage(page,pageSize);
        String userId = video.getUserId();
        List<Videos> videos = videosMapper.showMyLike(userId);
        PageInfo pageInfo = new PageInfo(videos);
        PagedResult result = new PagedResult();
        result.setRows(videos);
        result.setTotal(pageInfo.getPages());
        result.setPage(pageInfo.getPageNum());
        result.setRecords(pageInfo.getSize());

        return result;
    }


}
