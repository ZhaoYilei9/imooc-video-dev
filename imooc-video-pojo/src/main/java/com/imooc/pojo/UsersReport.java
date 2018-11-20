package com.imooc.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "users_report")
public class UsersReport {
    @Id
    private String id;

    /**
     * id
     */
    @Column(name = "deal_user_id")
    private String dealUserId;

    @Column(name = "deal_video_id")
    private String dealVideoId;

    /**
     *  
     */
    private String title;

    private String content;

    /**
     * id
     */
    private String userid;

    @Column(name = "create_date")
    private Date createDate;

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
     * @return deal_user_id - id
     */
    public String getDealUserId() {
        return dealUserId;
    }

    /**
     * 设置id
     *
     * @param dealUserId id
     */
    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }

    /**
     * @return deal_video_id
     */
    public String getDealVideoId() {
        return dealVideoId;
    }

    /**
     * @param dealVideoId
     */
    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId;
    }

    /**
     * 获取 
     *
     * @return title -  
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 
     *
     * @param title  
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取id
     *
     * @return userid - id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置id
     *
     * @param userid id
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}