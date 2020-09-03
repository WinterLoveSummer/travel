package com.travel.service.scene.entity.vo;

import com.travel.common.domain.TravelUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author lhy
 * @date 2020/3/22 18:48
 * @desc: 评论信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentsVo {
    private Integer id;
    private Integer sceneId;
    private String userCode;
    private TravelUser travelUser;
    private String content;
    private Integer grade;
    private List<String> imageUrls;
    private Date createTime;
}
