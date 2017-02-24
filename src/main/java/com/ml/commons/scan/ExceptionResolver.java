package com.ml.commons.scan;

import com.ml.commons.result.Result;
import com.ml.commons.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

    @Autowired
    private JacksonObjectMapper jacksonObjectMapper;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        LOGGER.error(e.getMessage(), e);
        if (!(handler instanceof HandlerMethod))
            return new ModelAndView("error/500");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (WebUtils.isAjax(handlerMethod)) {
            Result result = new Result();
            result.setMsg(e.getMessage());
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            view.setObjectMapper(jacksonObjectMapper);
            view.setContentType("text/html;charset=UTF-8");
            return new ModelAndView(view, BeanMap.create(result));
        }
        return new ModelAndView("error/500").addObject("error" , e.getMessage());
    }

}