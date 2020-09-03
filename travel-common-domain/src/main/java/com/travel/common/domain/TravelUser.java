package com.travel.common.domain;

import com.travel.common.BaseDomain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "travel_user")
public class TravelUser extends BaseDomain {
    private static final long serialVersionUID = -8820168441871893545L;
    /**
     * 用户编码
     */
    @Id
    @Column(name = "user_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private String userCode;

    /**
     * 登录账号
     */
    @Column(name = "login_code")
    private String loginCode;

    /**
     * 用户昵称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登录密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 出生年月
     */
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
     * 头像路径
     */
    private String avatar;

    /**
     * 个性签名
     */
    @Column(name = "SIGN")
    private String sign;

    /**
     * 绑定的微信号
     */
    @Column(name = "wx_openid")
    private String wxOpenid;


    /**
     * 密码安全级别（0初始 1很弱 2弱 3安全 4很安全）
     */
    @Column(name = "pwd_security_level")
    private Short pwdSecurityLevel;

    /**
     * 密码修改记录
     */
    @Column(name = "pwd_update_record")
    private String pwdUpdateRecord;

    /**
     * 密保问题
     */
    @Column(name = "pwd_question")
    private String pwdQuestion;

    /**
     * 密保问题答案
     */
    @Column(name = "pwd_question_answer")
    private String pwdQuestionAnswer;

    /**
     * 密保问题2
     */
    @Column(name = "pwd_question_2")
    private String pwdQuestion2;

    /**
     * 密保问题答案2
     */
    @Column(name = "pwd_question_answer_2")
    private String pwdQuestionAnswer2;

    /**
     * 密保问题3
     */
    @Column(name = "pwd_question_3")
    private String pwdQuestion3;

    /**
     * 密保问题答案3
     */
    @Column(name = "pwd_question_answer_3")
    private String pwdQuestionAnswer3;

    /**
     * 密码问题修改时间
     */
    @Column(name = "pwd_quest_update_date")
    private Date pwdQuestUpdateDate;

    /**
     * 最后登陆IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登陆时间
     */
    @Column(name = "last_login_date")
    private Date lastLoginDate;

    /**
     * 冻结时间
     */
    @Column(name = "freeze_date")
    private Date freezeDate;

    /**
     * 冻结原因
     */
    @Column(name = "freeze_cause")
    private String freezeCause;

    /**
     * 状态（0正常 1删除 2停用 3冻结）
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取用户编码
     *
     * @return user_code - 用户编码
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户编码
     *
     * @param userCode 用户编码
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
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
     * 获取用户昵称
     *
     * @return user_name - 用户昵称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户昵称
     *
     * @param userName 用户昵称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取登录密码
     *
     * @return PASSWORD - 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取电子邮箱
     *
     * @return email - 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮箱
     *
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取用户性别
     *
     * @return sex - 用户性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置用户性别
     *
     * @param sex 用户性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取用户年龄
     *
     * @return age - 用户年龄
     */
    public Date getDateBirth() {
        return dateBirth;
    }

    /**
     * 设置用户年龄
     *

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
     * 获取头像路径
     *
     * @return avatar - 头像路径
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像路径
     *
     * @param avatar 头像路径
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取个性签名
     *
     * @return SIGN - 个性签名
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置个性签名
     *
     * @param sign 个性签名
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取绑定的微信号
     *
     * @return wx_openid - 绑定的微信号
     */
    public String getWxOpenid() {
        return wxOpenid;
    }

    /**
     * 设置绑定的微信号
     *
     * @param wxOpenid 绑定的微信号
     */
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }



    /**
     * 获取密码安全级别（0初始 1很弱 2弱 3安全 4很安全）
     *
     * @return pwd_security_level - 密码安全级别（0初始 1很弱 2弱 3安全 4很安全）
     */
    public Short getPwdSecurityLevel() {
        return pwdSecurityLevel;
    }

    /**
     * 设置密码安全级别（0初始 1很弱 2弱 3安全 4很安全）
     *
     * @param pwdSecurityLevel 密码安全级别（0初始 1很弱 2弱 3安全 4很安全）
     */
    public void setPwdSecurityLevel(Short pwdSecurityLevel) {
        this.pwdSecurityLevel = pwdSecurityLevel;
    }

    /**
     * 获取密码修改记录
     *
     * @return pwd_update_record - 密码修改记录
     */
    public String getPwdUpdateRecord() {
        return pwdUpdateRecord;
    }

    /**
     * 设置密码修改记录
     *
     * @param pwdUpdateRecord 密码修改记录
     */
    public void setPwdUpdateRecord(String pwdUpdateRecord) {
        this.pwdUpdateRecord = pwdUpdateRecord;
    }

    /**
     * 获取密保问题
     *
     * @return pwd_question - 密保问题
     */
    public String getPwdQuestion() {
        return pwdQuestion;
    }

    /**
     * 设置密保问题
     *
     * @param pwdQuestion 密保问题
     */
    public void setPwdQuestion(String pwdQuestion) {
        this.pwdQuestion = pwdQuestion;
    }

    /**
     * 获取密保问题答案
     *
     * @return pwd_question_answer - 密保问题答案
     */
    public String getPwdQuestionAnswer() {
        return pwdQuestionAnswer;
    }

    /**
     * 设置密保问题答案
     *
     * @param pwdQuestionAnswer 密保问题答案
     */
    public void setPwdQuestionAnswer(String pwdQuestionAnswer) {
        this.pwdQuestionAnswer = pwdQuestionAnswer;
    }

    /**
     * 获取密保问题2
     *
     * @return pwd_question_2 - 密保问题2
     */
    public String getPwdQuestion2() {
        return pwdQuestion2;
    }

    /**
     * 设置密保问题2
     *
     * @param pwdQuestion2 密保问题2
     */
    public void setPwdQuestion2(String pwdQuestion2) {
        this.pwdQuestion2 = pwdQuestion2;
    }

    /**
     * 获取密保问题答案2
     *
     * @return pwd_question_answer_2 - 密保问题答案2
     */
    public String getPwdQuestionAnswer2() {
        return pwdQuestionAnswer2;
    }

    /**
     * 设置密保问题答案2
     *
     * @param pwdQuestionAnswer2 密保问题答案2
     */
    public void setPwdQuestionAnswer2(String pwdQuestionAnswer2) {
        this.pwdQuestionAnswer2 = pwdQuestionAnswer2;
    }

    /**
     * 获取密保问题3
     *
     * @return pwd_question_3 - 密保问题3
     */
    public String getPwdQuestion3() {
        return pwdQuestion3;
    }

    /**
     * 设置密保问题3
     *
     * @param pwdQuestion3 密保问题3
     */
    public void setPwdQuestion3(String pwdQuestion3) {
        this.pwdQuestion3 = pwdQuestion3;
    }

    /**
     * 获取密保问题答案3
     *
     * @return pwd_question_answer_3 - 密保问题答案3
     */
    public String getPwdQuestionAnswer3() {
        return pwdQuestionAnswer3;
    }

    /**
     * 设置密保问题答案3
     *
     * @param pwdQuestionAnswer3 密保问题答案3
     */
    public void setPwdQuestionAnswer3(String pwdQuestionAnswer3) {
        this.pwdQuestionAnswer3 = pwdQuestionAnswer3;
    }

    /**
     * 获取密码问题修改时间
     *
     * @return pwd_quest_update_date - 密码问题修改时间
     */
    public Date getPwdQuestUpdateDate() {
        return pwdQuestUpdateDate;
    }

    /**
     * 设置密码问题修改时间
     *
     * @param pwdQuestUpdateDate 密码问题修改时间
     */
    public void setPwdQuestUpdateDate(Date pwdQuestUpdateDate) {
        this.pwdQuestUpdateDate = pwdQuestUpdateDate;
    }

    /**
     * 获取最后登陆IP
     *
     * @return last_login_ip - 最后登陆IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置最后登陆IP
     *
     * @param lastLoginIp 最后登陆IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取最后登陆时间
     *
     * @return last_login_date - 最后登陆时间
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * 设置最后登陆时间
     *
     * @param lastLoginDate 最后登陆时间
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * 获取冻结时间
     *
     * @return freeze_date - 冻结时间
     */
    public Date getFreezeDate() {
        return freezeDate;
    }

    /**
     * 设置冻结时间
     *
     * @param freezeDate 冻结时间
     */
    public void setFreezeDate(Date freezeDate) {
        this.freezeDate = freezeDate;
    }

    /**
     * 获取冻结原因
     *
     * @return freeze_cause - 冻结原因
     */
    public String getFreezeCause() {
        return freezeCause;
    }

    /**
     * 设置冻结原因
     *
     * @param freezeCause 冻结原因
     */
    public void setFreezeCause(String freezeCause) {
        this.freezeCause = freezeCause;
    }

    /**
     * 获取状态（0正常 1删除 2停用 3冻结）
     *
     * @return STATUS - 状态（0正常 1删除 2停用 3冻结）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态（0正常 1删除 2停用 3冻结）
     *
     * @param status 状态（0正常 1删除 2停用 3冻结）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建者
     *
     * @return create_by - 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建者
     *
     * @param createBy 创建者
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
     * 获取更新者
     *
     * @return update_by - 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新者
     *
     * @param updateBy 更新者
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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
}