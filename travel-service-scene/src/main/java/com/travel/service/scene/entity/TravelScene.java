package com.travel.service.scene.entity;

import com.travel.common.BaseDomain;

import javax.persistence.*;

@Table(name = "travel_scene")
public class TravelScene extends BaseDomain {
    /**
     * 景点id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 类型
     */
    private String catename;

    /**
     * 景点名称
     */
    private String cnname;

    /**
     * 景点等级
     */
    private Integer hotgrade;

    /**
     * 景点图片
     */
    private String photo;

    /**
     * 评分
     */
    private Double grade;

    /**
     * 评论数
     */
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 获取景点id
     *
     * @return id - 景点id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置景点id
     *
     * @param id 景点id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类型
     *
     * @return catename - 类型
     */
    public String getCatename() {
        return catename;
    }

    /**
     * 设置类型
     *
     * @param catename 类型
     */
    public void setCatename(String catename) {
        this.catename = catename;
    }

    /**
     * 获取景点名称
     *
     * @return cnname - 景点名称
     */
    public String getCnname() {
        return cnname;
    }

    /**
     * 设置景点名称
     *
     * @param cnname 景点名称
     */
    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    /**
     * 获取景点等级
     *
     * @return hotgrade - 景点等级
     */
    public Integer getHotgrade() {
        return hotgrade;
    }

    /**
     * 设置景点等级
     *
     * @param hotgrade 景点等级
     */
    public void setHotgrade(Integer hotgrade) {
        this.hotgrade = hotgrade;
    }

    /**
     * 获取景点图片
     *
     * @return photo - 景点图片
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置景点图片
     *
     * @param photo 景点图片
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 获取评分
     *
     * @return grade - 评分
     */
    public Double getGrade() {
        return grade;
    }

    /**
     * 设置评分
     *
     * @param grade 评分
     */
    public void setGrade(Double grade) {
        this.grade = grade;
    }

    /**
     * 获取评论数
     *
     * @return comment_count - 评论数
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 设置评论数
     *
     * @param commentCount 评论数
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 获取所在城市
     *
     * @return city - 所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置所在城市
     *
     * @param city 所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "TravelScene{" +
                "id=" + id +
                ", catename='" + catename + '\'' +
                ", cnname='" + cnname + '\'' +
                ", hotgrade=" + hotgrade +
                ", photo='" + photo + '\'' +
                ", grade=" + grade +
                ", commentCount=" + commentCount +
                ", city='" + city + '\'' +
                '}';
    }
}