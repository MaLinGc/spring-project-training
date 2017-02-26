package com.ml.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class IdUtil {

    public static String randomUUID() {
        return StringUtils.remove(UUID.randomUUID().toString(), "-");
    }
}
