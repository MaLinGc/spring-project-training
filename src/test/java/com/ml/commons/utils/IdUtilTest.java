package com.ml.commons.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class IdUtilTest {
    @Test
    public void randomUUID() throws Exception {
        for (int i = 0; i < 3; i++) {
            System.out.println(IdUtil.randomUUID());
        }
    }

}