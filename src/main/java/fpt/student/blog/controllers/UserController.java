package fpt.student.blog.controllers;

import fpt.student.blog.entities.Blogs;
import fpt.student.blog.entities.Users;
import fpt.student.blog.models.LoginDTO;
import fpt.student.blog.services.BLogService;
import fpt.student.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final BLogService bLogService;

    @Autowired
    public UserController(UserService userService, BLogService bLogService){
        this.userService = userService;
        this.bLogService = bLogService;
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("listBlogs")
    @ResponseBody
    public List<Blogs> listBlogs(){
        return bLogService.findAll();
    }
}
