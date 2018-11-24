package com.imooc.vo;

import com.imooc.pojo.Videos;

public class VideosVo extends Videos {
    private String faceImage;
    private String nickname;

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;

    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
