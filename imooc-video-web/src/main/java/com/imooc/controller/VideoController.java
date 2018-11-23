package com.imooc.controller;

import com.imooc.pojo.Bgm;
import com.imooc.pojo.Videos;
import com.imooc.service.BgmService;
import com.imooc.service.VideoService;
import com.imooc.utils.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("video")
public class VideoController extends BaseController{

    @Autowired
    private BgmService bgmService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private Sid sid;

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
        String DBDir = userId + "/video/";
        String DBPath = userId + "/video/" + fileName;
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
                String mergeVideoName = videos.getId() + ".avi";
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
        System.out.println(DBCover);







        return IMoocJSONResult.ok();
    }

}
