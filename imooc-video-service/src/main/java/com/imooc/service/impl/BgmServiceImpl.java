package com.imooc.service.impl;

import com.imooc.mapper.BgmMapper;
import com.imooc.pojo.Bgm;
import com.imooc.service.BgmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BgmServiceImpl implements BgmService {

    @Autowired
    private BgmMapper bgmMapper;

    @Override
    public List<Bgm> getAll() {
        List<Bgm> bgms = bgmMapper.selectAll();
        return bgms;
    }
}
