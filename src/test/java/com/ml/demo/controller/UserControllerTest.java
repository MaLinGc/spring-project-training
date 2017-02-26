package com.ml.demo.controller;

import com.ml.commons.base.BaseControllerTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserControllerTest extends BaseControllerTest {

    @Test
    public void users() throws Exception {
        mockMvc.perform(get("/user/list")).andReturn();
    }

    @Test
    public void add() throws Exception {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("name", "maling");
        paramMap.put("age", "24");
        paramMap.put("email", "maling@scbit.org");
        paramMap.put("address", "科苑路1278号");
        paramMap.put("gender", "男");

        post = post("/user/add");
        addParams(paramMap);
        mockMvc.perform(post).andReturn();
    }

    @Test
    public void edit() throws Exception {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", "168a42b9d2184e8296032e186d970123");
        paramMap.put("name", "Administrator");
        paramMap.put("age", "30");
        paramMap.put("email", "administrator@scbit.org");
        paramMap.put("address", "科苑路1278号");
        paramMap.put("gender", "男");

        post = post("/user/edit");
        addParams(paramMap);
        mockMvc.perform(post).andReturn();
    }

    @Test
    public void delete() throws Exception {
        post = post("/user/delete?id=9de358a6-1e73-4d41-9ac1-85e7c7ee47b7");
        mockMvc.perform(post).andReturn();
    }

}