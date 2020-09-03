package com.travel.service.sso.entity.dto;

import com.travel.service.sso.entity.DynamicImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lhy
 * @date 2020/5/4 14:28
 * @desc:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DynamicReq {
    private String title;
    private String main;
    private List<String> images;
}
