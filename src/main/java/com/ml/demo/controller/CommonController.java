package com.ml.demo.controller;

import com.ml.commons.utils.CaptchaUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonController {

    @RequestMapping(path = {"/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * 图形验证码
     */
    @GetMapping(path = "/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        CaptchaUtils.generate(request, response);
    }

    @GetMapping(path = "/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping(path = "/login")
    public String login(String email, String password) {
        return "redirect:/user/manager";
    }

}
