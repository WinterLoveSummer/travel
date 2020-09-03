package com.travel.common.hystrix;
import com.travel.common.constants.HttpStatusConstants;
import com.travel.common.dto.BaseResult;
import com.travel.common.utils.MapperUtils;

/**
 * 通用的熔断方法
 */
public class Fallback {

    /**
     * 502错误  通用的熔断方法
     * @return
     */
    public static String badGateway(){
        BaseResult baseResult = BaseResult.failed(HttpStatusConstants.BAD_GATEWAY);
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
