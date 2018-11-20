package com.imooc.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Videos {
    @Id
    private String id;

    /**
     * id
     */
    @Column(name = "user_id")
    private String userId;

    @Column(name = "audio_id")
    private String audioId;

    @Column(name = "video_desc")
    private String videoDesc;

    @Column(name = "video_path")
    private String videoPath;

    @Column(name = "video_seconds")
    private Float videoSeconds;

    @Column(name = "video_width")
    private Integer videoWidth;

    @Column(name = "video_height")
    private Integer videoHeight;

    @Column(name = "cover_path")
    private String coverPath;

    /**
     * /
     */
    @Column(name = "like_counts")
    private Long likeCounts;

    /**
     * 
1
2
     */
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取id
     *
     * @return user_id - id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置id
     *
     * @param userId id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return audio_id
     */
    public String getAudioId() {
        return audioId;
    }

    /**
     * @param audioId
     */
    public void setAudioId(String audioId) {
        this.audioId = audioId;
    }

    /**
     * @return video_desc
     */
    public String getVideoDesc() {
        return videoDesc;
    }

    /**
     * @param videoDesc
     */
    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    /**
     * @return video_path
     */
    public String getVideoPath() {
        return videoPath;
    }

    /**
     * @param videoPath
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * @return video_seconds
     */
    public Float getVideoSeconds() {
        return videoSeconds;
    }

    /**
     * @param videoSeconds
     */
    public void setVideoSeconds(Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    /**
     * @return video_width
     */
    public Integer getVideoWidth() {
        return videoWidth;
    }

    /**
     * @param videoWidth
     */
    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    /**
     * @return video_height
     */
    public Integer getVideoHeight() {
        return videoHeight;
    }

    /**
     * @param videoHeight
     */
    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    /**
     * @return cover_path
     */
    public String getCoverPath() {
        return coverPath;
    }

    /**
     * @param coverPath
     */
    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    /**
     * 获取/
     *
     * @return like_counts - /
     */
    public Long getLikeCounts() {
        return likeCounts;
    }

    /**
     * 设置/
     *
     * @param likeCounts /
     */
    public void setLikeCounts(Long likeCounts) {
        this.likeCounts = likeCounts;
    }

    /**
     * 获取
1
2
     *
     * @return status - 
1
2
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置
1
2
     *
     * @param status 
1
2
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}