package fpt.student.blog.controllers;

import com.restfb.types.User;
import fpt.student.blog.entities.Users;
import fpt.student.blog.models.GooglePojo;
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
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("auth/google")
    public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException{
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()){
            return "redirect:/login?google=error";
        }

        String accessToken = googleUtils.getToken(code);

        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
        Users users = userService.findByEmail(googlePojo.getEmail());
        if (users == null){
            users = new Users();
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getName(), users.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/home";
    }

    @GetMapping("auth/facebook")
    public String loginFacebook(HttpServletRequest request) throws ClientProtocolException, IOException{
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()){
            return "redirect:/login?facebook=error";
        }

        String accessToken = restFB.getToken(code);
        User userFacebook = restFB.getUserInfo(accessToken);
        Users users = userService.findByEmail(userFacebook.getEmail());
        System.out.println(userFacebook.getEmail());
        if (users == null){
            users = new Users();
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(users.getName(), users.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/home";
    }
}
