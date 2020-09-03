package com.travel.service.sso.entity;

import com.travel.common.BaseDomain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "travel_dynamic")
public class TravelDynamic extends BaseDomain {
    /**
     * 动态编码
     */
    @Id
    @Column(name = "dynamic_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String dynamicCode;

    /**
     * 用户编码
     */
    @Column(name = "login_code")
    private String loginCode;

    /**
     * 动态标题
     */
    @Column(name = "dynamic_title")
    private String dynamicTitle;

    /**
     * 被赞数
     */
    @Column(name = "number_of_upvotes")
    private Integer numberOfUpvotes;

    /**
     * 被踩数
     */
    @Column(name = "number_of_downvotes")
    private Integer numberOfDownvotes;

    /**
     * 被阅读数
     */
    @Column(name = "number_of_reads")
    private Integer numberOfReads;

    /**
     * 被评论数
     */
    @Column(name = "number_of_comments")
    private Integer numberOfComments;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 状态（0正常 1删除）
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 动态正文
     */
    @Column(name = "dynamic_main")
    private String dynamicMain;

    /**
     * 获取动态编码
     *
     * @return dynamic_code - 动态编码
     */
    public String getDynamicCode() {
        return dynamicCode;
    }

    /**
     * 设置动态编码
     *
     * @param dynamicCode 动态编码
     */
    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }

    /**
     * 获取用户编码
     *
     * @return login_code - 用户编码
     */
    public String getLoginCode() {
        return loginCode;
    }

    /**
     * 设置用户编码
     *
     * @param loginCode 用户编码
     */
    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    /**
     * 获取动态标题
     *
     * @return dynamic_title - 动态标题
     */
    public String getDynamicTitle() {
        return dynamicTitle;
    }

    /**
     * 设置动态标题
     *
     * @param dynamicTitle 动态标题
     */
    public void setDynamicTitle(String dynamicTitle) {
        this.dynamicTitle = dynamicTitle;
    }

    /**
     * 获取被赞数
     *
     * @return number_of_upvotes - 被赞数
     */
    public Integer getNumberOfUpvotes() {
        return numberOfUpvotes;
    }

    /**
     * 设置被赞数
     *
     * @param numberOfUpvotes 被赞数
     */
    public void setNumberOfUpvotes(Integer numberOfUpvotes) {
        this.numberOfUpvotes = numberOfUpvotes;
    }

    /**
     * 获取被踩数
     *
     * @return number_of_downvotes - 被踩数
     */
    public Integer getNumberOfDownvotes() {
        return numberOfDownvotes;
    }

    /**
     * 设置被踩数
     *
     * @param numberOfDownvotes 被踩数
     */
    public void setNumberOfDownvotes(Integer numberOfDownvotes) {
        this.numberOfDownvotes = numberOfDownvotes;
    }

    /**
     * 获取被阅读数
     *
     * @return number_of_reads - 被阅读数
     */
    public Integer getNumberOfReads() {
        return numberOfReads;
    }

    /**
     * 设置被阅读数
     *
     * @param numberOfReads 被阅读数
     */
    public void setNumberOfReads(Integer numberOfReads) {
        this.numberOfReads = numberOfReads;
    }

    /**
     * 获取被评论数
     *
     * @return number_of_comments - 被评论数
     */
    public Integer getNumberOfComments() {
        return numberOfComments;
    }

    /**
     * 设置被评论数
     *
     * @param numberOfComments 被评论数
     */
    public void setNumberOfComments(Integer numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取状态（0正常 1删除）
     *
     * @return STATUS - 状态（0正常 1删除）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态（0正常 1删除）
     *
     * @param status 状态（0正常 1删除）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取动态正文
     *
     * @return dynamic_main - 动态正文
     */
    public String getDynamicMain() {
        return dynamicMain;
    }

    /**
     * 设置动态正文
     *
     * @param dynamicMain 动态正文
     */
    public void setDynamicMain(String dynamicMain) {
        this.dynamicMain = dynamicMain;
    }
}