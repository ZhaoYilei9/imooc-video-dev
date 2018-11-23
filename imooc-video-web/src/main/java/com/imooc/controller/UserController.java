package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.IMoocJSONResult;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;
    //上传头像
    @PostMapping("upload")
    public IMoocJSONResult uploadFaceImage(String userId, MultipartFile file) {
        if (file == null){
            return IMoocJSONResult.errorMsg("");
        }
        String fileSpace = FILE_SPACE;
        String fileDir = fileSpace + userId + "/face/";
        String fileName = file.getOriginalFilename();
        String filePath = fileDir + fileName;
        String DBPath = userId + "/face/" + fileName;
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
        Users users = userService.queryUserByPrimaryKey(userId);
        users.setFaceImage(DBPath);
        userService.updateUser(users);


        return IMoocJSONResult.ok(users);

    }

    @PostMapping("query")
    public IMoocJSONResult getUserInfo(String userId, String fanId){

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(fanId)){
            return IMoocJSONResult.errorMsg("");
        }
        if (userId.equals(fanId)){
            Users users = userService.queryUserByPrimaryKey(userId);
            if (users != null){
                return IMoocJSONResult.ok(users);
            }else {
                return IMoocJSONResult.errorMsg("");
            }
        }

        return IMoocJSONResult.ok();
    }

}
