package com.ml.commons.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath:spring-config.xml"),
        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")
})
public abstract class BaseControllerTest {

    protected MockMvc mockMvc;
    protected MockHttpServletRequest request;
    protected MockHttpServletResponse response;
    protected MockHttpServletRequestBuilder post;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).alwaysDo(MockMvcResultHandlers.print()).build();
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    public MockHttpServletRequestBuilder addParams(Map<String, String> paramMap) {
        if (paramMap == null || paramMap.isEmpty())
            return this.post;
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            post.param(entry.getKey(), entry.getValue());
        }
        return this.post;
    }
}
