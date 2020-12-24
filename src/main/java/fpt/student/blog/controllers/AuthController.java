package fpt.student.blog.controllers;

import com.restfb.types.User;
import fpt.student.blog.entities.Role;
import fpt.student.blog.entities.Users;
import fpt.student.blog.models.GooglePojo;
import fpt.student.blog.models.UserDTO;
import fpt.student.blog.services.RoleService;
import fpt.student.blog.services.UserService;
import fpt.student.blog.utils.GoogleUtils;
import fpt.student.blog.utils.RestFB;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class AuthController {
    @Autowired
    private GoogleUtils googleUtils;

    @Autowired
    private RestFB restFB;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleService roleService;
    @GetMapping("auth/google")
    public String loginGoogle(HttpServletRequest request, ModelMap model) throws ClientProtocolException, IOException{
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()){
            return "redirect:/login?google=error";
        }

        String accessToken = googleUtils.getToken(code);

        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
        Users users = userService.findByEmail(googlePojo.getEmail());
        if (users == null){
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(googlePojo.getEmail());
            userDTO.setName(googlePojo.getName());
            model.addAttribute("userDTO", userDTO);
            return "signup";
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getName(), users.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/home";
    }

    @GetMapping("auth/facebook")
    public String loginFacebook(HttpServletRequest request, ModelMap model) throws ClientProtocolException, IOException{
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()){
            return "redirect:/login?facebook=error";
        }

        String accessToken = restFB.getToken(code);
        User userFacebook = restFB.getUserInfo(accessToken);
        Users users = userService.findByEmail(userFacebook.getEmail());

        if (users == null){
            UserDTO userDTO = new UserDTO();
            userDTO.setName(userFacebook.getName());
            userDTO.setEmail(userFacebook.getEmail());
            model.addAttribute("userDTO", userDTO);
            return "signup";
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getName(), users.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/home";
    }

    @GetMapping("signup")
    public String signPage(ModelMap model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "signup";
    }

    @PostMapping("signup")
    public String signup(ModelMap model, @ModelAttribute("userDTO") @Validated UserDTO userDTO, BindingResult result){
        if (result.hasErrors()){
            return "signup";
        }
        Users user = userService.findByName(userDTO.getName());
        if (user != null){
            model.addAttribute("message", "Name "+ userDTO.getName()+ " already existed!");
            return "signup";
        }
        user = userService.findByEmail(userDTO.getEmail());
        if (user != null){
            model.addAttribute("message","Email " + userDTO.getEmail()+ " already existed!");
            return "signup";
        }

        user = new Users();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        Role role = roleService.findByName("USER").orElse(new Role("USER"));
        user.setRole(role);
        userService.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/home";
    }
}
