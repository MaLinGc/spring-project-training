package com.ml.commons.utils;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

public class WebUtils extends org.springframework.web.util.WebUtils {
    public static boolean isAjax(HandlerMethod handlerMethod) {
        ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
        if (null != responseBody) {
            return true;
        }
        RestController restAnnotation = handlerMethod.getBean().getClass().getAnnotation(RestController.class);
        if (null != restAnnotation) {
            return true;
        }
        return false;
    }
}
