package com.kdg.springprojt5.controllers.mvc;

import com.kdg.springprojt5.controllers.mvc.helper.HistoryItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login(Model model, HttpSession session) {
        setHistory(session, "Login");
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("title", "Login");
        mav.addObject("headerList", new ArrayList<>(List.of()));
        mav.addObject("footerList", new ArrayList<>(List.of()));
        return mav;
    }

    private void setHistory(HttpSession session, String message){
        List<HistoryItem> history = (List<HistoryItem>) session.getAttribute("history");
        if (session.getAttribute("history") == null) {
            history = new ArrayList<>();
        }
        history.add(new HistoryItem(message, LocalDateTime.now()));
        session.setAttribute("history", history);
    }
}
