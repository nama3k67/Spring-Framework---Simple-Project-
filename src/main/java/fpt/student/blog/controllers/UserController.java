package fpt.student.blog.controllers;

import fpt.student.blog.entities.Blogs;
import fpt.student.blog.entities.Role;
import fpt.student.blog.entities.Users;
import fpt.student.blog.models.UserDTO;
import fpt.student.blog.services.BLogService;
import fpt.student.blog.services.RoleService;
import fpt.student.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("profile")
    public String showProfile(ModelMap model){
        UserDetails principal =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Users user = userService.findByName(principal.getUsername());
        List<Blogs> blogs = bLogService.findByUsers(user);
        model.addAttribute("user", user);
        model.addAttribute("blogs", blogs);
        return "profile";
    }

}
