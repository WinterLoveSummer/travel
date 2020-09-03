package com.travel.service.scene.entity;

import javax.persistence.*;

@Table(name = "comments_image")
public class CommentsImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 评论id
     */
    @Column(name = "comments_id")
    private Integer commentsId;

    /**
     * 图片地址
     */
    private String photo;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取评论id
     *
     * @return comments_id - 评论id
     */
    public Integer getCommentsId() {
        return commentsId;
    }

    /**
     * 设置评论id
     *
     * @param commentsId 评论id
     */
    public void setCommentsId(Integer commentsId) {
        this.commentsId = commentsId;
    }

    /**
     * 获取图片地址
     *
     * @return photo - 图片地址
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置图片地址
     *
     * @param photo 图片地址
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}