package fpt.student.blog.services.impl;

import fpt.student.blog.entities.Role;
import fpt.student.blog.entities.Users;
import fpt.student.blog.services.RoleService;
import fpt.student.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = userService.findByName(userName);
        if (user == null){
            throw new UsernameNotFoundException("User not found with username "+ userName);
        }

        Role role = roleService.findById(user.getRole().getId()).orElse(null);
        String roleName;
        if (role == null){
            roleName = "USER";
        }else {
            roleName = role.getName();
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        grantList.add(authority);

        UserDetails userDetails = (UserDetails) new User(user.getName(), passwordEncoder.encode(user.getPassword()), grantList);

        return userDetails;
    }
}
