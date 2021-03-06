package com.imooc.controller;

import com.imooc.pojo.Bgm;
import com.imooc.pojo.Videos;
import com.imooc.service.BgmService;
import com.imooc.service.VideoService;
import com.imooc.utils.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("video")
public class VideoController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private BgmService bgmService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private Sid sid;
    //查询视频列表video/list?page=1&pageSize=7&isSaveRecord=0
    @PostMapping("showAll")
    public IMoocJSONResult showAll(int page,int pageSize,int isSaveRecord,@RequestBody Videos videos){
        PagedResult result = videoService.showAll(page, pageSize, isSaveRecord, videos);
        return IMoocJSONResult.ok(result);
    }
    @PostMapping("listMyWork")
    public IMoocJSONResult videoList(int page,int pageSize,@RequestBody Videos videos){
        log.info("page:{}",page);
        log.info("pageSize:{}",pageSize);
        log.info("userID :{}",videos.getUserId());
        PagedResult result = videoService.queryVideoList(page, pageSize,videos);
        return IMoocJSONResult.ok(result);
    }
    @PostMapping("listMyLike")
    public IMoocJSONResult showMyLike(int page,int pageSize,@RequestBody Videos videos){
        log.info("mylike:{}",page);
        log.info("pageSize:{}",pageSize);
        log.info("userID:{}",videos.getUserId());
        PagedResult result = videoService.showMyLike(page,pageSize,videos);

        return IMoocJSONResult.ok(result);
    }


    //上传与合并视频
    @PostMapping("upload")
    public IMoocJSONResult uploadVideo(Videos videos, MultipartFile file) throws Exception{
        if (file == null){
            return IMoocJSONResult.errorMsg("");
        }
        String bgmId = videos.getAudioId();
        String userId = videos.getUserId();
        String fileSpace = FILE_SPACE;
        String fileDir = fileSpace  + userId + "/video/";
        String fileName = file.getOriginalFilename();
        String filePath = fileDir + fileName;
        String DBDir = "/" + userId + "/video/";
        String DBPath = DBDir + fileName;
        File outFile = new File(filePath);
        if (outFile.getParentFile() != null && !outFile.getParentFile().isDirectory()){
            //mkdir()是错误的,mkdirs()可以递归创建
            outFile.getParentFile().mkdirs();
        }
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(outFile);
            IOUtils.copy(inputStream,outputStream);
            videos.setVideoPath(DBPath);
            String id = sid.nextShort();
            videos.setId(id);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (inputStream != null){
                try {
                    outputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
        if (StringUtils.isNotBlank(bgmId)){
            Bgm bgm = bgmService.getById(bgmId);
            if (bgm != null){
                String bgmPath = fileSpace + bgm.getPath();
//                MyFFMpegTest ffMpeg = new MyFFMpegTest();
                float mergeVideoTime = videos.getVideoSeconds();
                //avi格式是不能在线播放的
                String mergeVideoName = videos.getId() + ".mp4";
                String mergeVideoPath = fileDir + mergeVideoName;
                MergeVideoMp3 mergeVideoMp3 = new MergeVideoMp3();
                mergeVideoMp3.convertor(filePath,bgmPath,mergeVideoTime,mergeVideoPath);
                DBPath = DBDir + mergeVideoName;
                videos.setVideoPath(DBPath);
            }

        }
        String videoPath = fileSpace + videos.getVideoPath();
        String coverName = videos.getId() + ".jpg";
        String coverPath = fileDir + coverName;
        String DBCover = DBDir + coverName;
//        MyFetchCover fetchCover = new MyFetchCover();
//        fetchCover.getCover(videoPath,coverPath);
        FetchVideoCover fetchVideoCover = new FetchVideoCover();
        fetchVideoCover.getCover(videoPath,coverPath);
        videos.setCoverPath(DBCover);
//        System.out.println(DBCover);
        videos.setCreateTime(new Date());
        videos.setLikeCounts(0l);
        videos.setStatus(1);
        //千万别忘了保存到数据库奥！
        videoService.saveVideo(videos);
        return IMoocJSONResult.ok("上传成功");
    }

}
