package com.imooc.service;

import com.imooc.pojo.Videos;
import com.imooc.utils.PagedResult;

import java.util.List;

public interface VideoService {


    PagedResult queryVideoList(int page, int pageSize, int isSaveRecord);
}
