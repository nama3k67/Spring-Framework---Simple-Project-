package fpt.student.blog.controllers;

import fpt.student.blog.entities.Blogs;
import fpt.student.blog.entities.Users;
import fpt.student.blog.services.BLogService;
import fpt.student.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

//    @GetMapping("signup")
//    public String signupPage(ModelMap model, WebRequest request){
//        ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
//        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
//
//        UserDTO userDTO = null;
//        if (connection != null){
//            userDTO = new UserDTO(connection);
//        }else {
//            userDTO = new UserDTO();
//        }
//        model.addAttribute("userDTO", userDTO);
//        return "signup";
//    }
//
//    @PostMapping("signup")
//    public String signSave(WebRequest request,
//                           ModelMap model,
//                           @ModelAttribute("userDTO") @Validated UserDTO userDTO,
//                           BindingResult result,
//                           final RedirectAttributes redirectAttributes){
//        if (result.hasErrors()){
//            return "signup";
//        }
//        Users registered = null;
//
//        try{
//            registered = new Users();
//            Role role = roleService.findByName("USER").orElse(new Role("USER"));
//            registered.setRole(role);
//            registered.setEmail(userDTO.getEmail());
//            registered.setName(userDTO.getName());
//            registered.setPassword(userDTO.getPassword());
//        }catch (Exception e){
//            e.printStackTrace();
//            model.addAttribute("message", "Error" + e.getMessage());
//            return "signup";
//        }
//
//        if (userDTO.getProviderUserId() != null){
//            ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
//            providerSignInUtils.doPostSignUp(registered.getName(), request);
//        }
//        SecurityUtil.loginUser(registered, "USER");
//        return "redirect:/home";
//    }
}
