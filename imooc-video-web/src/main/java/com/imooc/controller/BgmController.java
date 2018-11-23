package com.imooc.controller;

import com.imooc.pojo.Bgm;
import com.imooc.service.BgmService;
import com.imooc.utils.IMoocJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bgm")
public class BgmController {

    @Autowired
    private BgmService bgmService;

    @GetMapping("list")
    public IMoocJSONResult getBgmList(){
        List<Bgm> bgms = bgmService.getAll();
        return IMoocJSONResult.ok(bgms);
    }


}
