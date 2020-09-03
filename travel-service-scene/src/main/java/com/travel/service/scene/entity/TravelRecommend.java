package com.travel.service.scene.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "travel_recommend")
public class TravelRecommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 出生日期
     */
    @Column(name = "date_birth")
    private Date dateBirth;

    /**
     * 用户职业
     */
    private String office;

    /**
     * 所在学校
     */
    private String school;

    /**
     * 登录账号
     */
    @Column(name = "login_code")
    private String loginCode;

    /**
     * 景点id
     */
    @Column(name = "scene_id")
    private Integer sceneId;

    /**
     * 权重
     */
    private Integer weight;

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
     * 获取出生日期
     *
     * @return date_birth - 出生日期
     */
    public Date getDateBirth() {
        return dateBirth;
    }

    /**
     * 设置出生日期
     *
     * @param dateBirth 出生日期
     */
    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    /**
     * 获取用户职业
     *
     * @return office - 用户职业
     */
    public String getOffice() {
        return office;
    }

    /**
     * 设置用户职业
     *
     * @param office 用户职业
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * 获取所在学校
     *
     * @return school - 所在学校
     */
    public String getSchool() {
        return school;
    }

    /**
     * 设置所在学校
     *
     * @param school 所在学校
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * 获取登录账号
     *
     * @return login_code - 登录账号
     */
    public String getLoginCode() {
        return loginCode;
    }

    /**
     * 设置登录账号
     *
     * @param loginCode 登录账号
     */
    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    /**
     * 获取景点id
     *
     * @return scene_id - 景点id
     */
    public Integer getSceneId() {
        return sceneId;
    }

    /**
     * 设置景点id
     *
     * @param sceneId 景点id
     */
    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * 获取权重
     *
     * @return weight - 权重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置权重
     *
     * @param weight 权重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}