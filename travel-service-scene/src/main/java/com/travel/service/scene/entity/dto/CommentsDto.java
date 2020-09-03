package com.travel.service.scene.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lhy
 * @date 2020/3/22 20:17
 * @desc:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDto {
    private Integer sceneId;
    private String content;
    private Integer grade;
    private List<String> images;
}
