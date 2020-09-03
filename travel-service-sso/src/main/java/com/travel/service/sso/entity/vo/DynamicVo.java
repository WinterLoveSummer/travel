package com.travel.service.sso.entity.vo;

import com.travel.common.domain.TravelUser;
import com.travel.service.sso.entity.DynamicImage;
import com.travel.service.sso.entity.TravelDynamic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author lhy
 * @date 2020/5/4 15:28
 * @desc: 论坛信息表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DynamicVo {
    private String dynamicCode;
    private String loginCode;
    private String dynamicTitle;
    private String dynamicMain;
    private Integer numberOfUpvotes;
    private Integer numberOfComments;
    private Date createTime;
    private String status;
    //用户信息
    private TravelUser travelUser;
    //论坛图片信息
    private List<String> dynamicImages;
}
