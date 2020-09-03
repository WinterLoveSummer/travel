package com.travel.service.sso.entity;

import javax.persistence.*;

@Table(name = "dynamic_image")
public class DynamicImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评论编码
     */
    @Column(name = "dynamic_code")
    private String dynamicCode;

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
     * 获取评论编码
     *
     * @return dynamic_code - 评论编码
     */
    public String getDynamicCode() {
        return dynamicCode;
    }

    /**
     * 设置评论编码
     *
     * @param dynamicCode 评论编码
     */
    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
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