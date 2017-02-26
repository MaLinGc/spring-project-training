package com.ml.demo.controller;

import com.ml.commons.base.BaseController;
import com.ml.commons.result.Result;
import com.ml.demo.entity.User;
import com.ml.demo.service.UserService;
import com.ml.demo.vo.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/manager")
    public String userPage() {
        return "admin/user";
    }

    @GetMapping(path = "/list")
    @ResponseBody
    public Page<User> users(Searchable searchable, Pageable pageable) {
        return userService.findBySearchable(searchable, pageable);
    }

    @GetMapping(path = "/addPage")
    public String addPage() {
        return "admin/userAdd";
    }

    @GetMapping(path = "/editPage")
    public String editPage(Model model, String id) {
        User user = userService.findOne(id);
        model.addAttribute("user", user);
        return "admin/userEdit";
    }

    @PostMapping(path = "/add")
    @ResponseBody
    public Result<String> add(User user) throws Exception {
        userService.addUser(user);
        return new Result<>(true);
    }

    @PostMapping(path = "/edit")
    @ResponseBody
    public Result<String> edit(User user) throws Exception {
        userService.updateUser(user);
        return new Result<>(true);
    }

    @PostMapping(path = "/delete")
    @ResponseBody
    public Result<String> delete(String id) {
        userService.delete(id);
        return new Result<>(true);
    }
}
