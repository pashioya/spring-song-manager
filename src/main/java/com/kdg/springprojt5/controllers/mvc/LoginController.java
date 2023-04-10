package com.kdg.springprojt5.controllers.mvc;

import com.kdg.springprojt5.controllers.api.dto.NewUserDto;
import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import com.kdg.springprojt5.domain.User;
import com.kdg.springprojt5.domain.UserRole;
import com.kdg.springprojt5.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public LoginController(UserService userService, BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    private void setHistory(HttpSession session, String message){
        List<HistoryItem> history = (List<HistoryItem>) session.getAttribute("history");
        if (session.getAttribute("history") == null) {
            history = new ArrayList<>();
        }
        history.add(new HistoryItem(message, LocalDateTime.now()));
        session.setAttribute("history", history);
    }

    @PostMapping("/register")
    public ModelAndView registerNewUser(
            @Valid NewUserDto userViewModel,
            HttpServletRequest request) throws ServletException {
        String encodedPassword = passwordEncoder.encode(userViewModel.getPassword());
        userService.saveUser(new User(userViewModel.getUsername(),
                encodedPassword, UserRole.valueOf(userViewModel.getRole())));
        request.login(
                userViewModel.getUsername(),
                encodedPassword);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/users/{username}")
    public UserDetails getUser(@PathVariable String username) {
        return userDetailsService.loadUserByUsername(username);
    }

    @GetMapping("/login")
    public ModelAndView login(HttpSession session) {
        setHistory(session, "Login");
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("title", "Login");
        mav.addObject("headerList", new ArrayList<>(List.of()));
        mav.addObject("footerList", new ArrayList<>(List.of()));
        return mav;
    }
}
