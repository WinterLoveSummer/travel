package com.travel.common.dto;

import com.travel.common.Code.CommonCode;
import com.travel.common.constants.HttpStatusConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult implements Serializable {

    private Integer status;
    private String message;
    private Object data;
    private Cursor cursor;


    //api接口返回参数
    public static BaseResult ok(Object data) {
        return createResult(CommonCode.SUCCESS, data, null);
    }

    public static BaseResult ok() {
        return createResult(CommonCode.SUCCESS, null, null);
    }

    public static BaseResult ok(CommonCode code) {
        return createResult(code, null, null);
    }


    public static BaseResult ok(Object data, Cursor cursor) {
        return createResult(CommonCode.SUCCESS, data, cursor);
    }

    public static BaseResult failed(CommonCode code) {
        return createResult(code, null, null);
    }

    public static BaseResult failed() {
        return createResult(CommonCode.ERROR, null, null);
    }


    //服务间调用返回
    public static BaseResult failed(HttpStatusConstants code) {
        return createResult(code, null, null);
    }


    //api接口返回数据
    private static BaseResult createResult(CommonCode code, Object Data, Cursor cursor) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(code.getKey());
        baseResult.setMessage(code.getValue());
        baseResult.setData(Data);
        baseResult.setCursor(cursor);
        return baseResult;
    }

    //api接口返回数据
    private static BaseResult createResult(HttpStatusConstants code, Object Data, Cursor cursor) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(code.getKey());
        baseResult.setMessage(code.getValue());
        baseResult.setData(Data);
        baseResult.setCursor(cursor);
        return baseResult;
    }


    @Data
    public static class Cursor {
        // 总条数
        private int total;
        // 第几页
        private int offset;
        // 每页显示条数
        private int limit;
    }

//    @Data
//    @Builder
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class Error{
//        private String field;
//        private String message;
//    }
}
