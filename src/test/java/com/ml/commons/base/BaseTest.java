package com.ml.commons.base;

import org.junit.Test;

import java.util.UUID;

public class BaseTest {

    @Test
    public void testUuid() {
        System.out.println(UUID.randomUUID().toString().length());
    }
}
