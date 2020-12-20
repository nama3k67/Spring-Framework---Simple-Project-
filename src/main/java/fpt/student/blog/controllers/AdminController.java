package fpt.student.blog.controllers;

import fpt.student.blog.entities.Users;
import fpt.student.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("admin")
    public String adminControl(ModelMap model){
        List<Users> users = (List<Users>)userService.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
