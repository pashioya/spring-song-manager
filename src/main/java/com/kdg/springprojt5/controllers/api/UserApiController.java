package com.kdg.springprojt5.controllers.api;

import com.kdg.springprojt5.controllers.api.dto.NewUserDto;
import com.kdg.springprojt5.domain.User;
import com.kdg.springprojt5.domain.UserRole;
import com.kdg.springprojt5.security.CustomUserDetailsService;
import com.kdg.springprojt5.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserApiController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailsService userDetailsService;

    public UserApiController(UserService userService, PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }
    
    
    @PostMapping("/register")
    public String registerNewUser(
            @Valid NewUserDto userViewModel,
            HttpServletRequest request) throws ServletException {
        String encodedPassword = passwordEncoder.encode(userViewModel.getPassword());
        userService.saveUser(new User(userViewModel.getUsername(),
                encodedPassword, UserRole.valueOf(userViewModel.getRole())));
        request.login(
                userViewModel.getUsername(),
                encodedPassword);
        return "redirect:/";
    }

    @GetMapping("/users/{username}")
    public UserDetails getUser(@PathVariable String username) {
        return userDetailsService.loadUserByUsername(username);
    }

}
