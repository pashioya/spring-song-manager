package com.kdg.springprojt5.controllers.mvc;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.ServerError;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleDatabaseException(Exception ex,Model model) {
        logger.error(ex.getMessage());
        logger.error(Arrays.toString(ex.getStackTrace()));
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("stackTrace", Arrays.toString(ex.getStackTrace()));
        model.addAttribute("title","Entity Error");
        return "entityError";
    }

    @ExceptionHandler(ServerError.class)
    public String handleException(Exception ex,Model model) {
        logger.error(ex.getMessage());
        logger.error(Arrays.toString(ex.getStackTrace()));
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("stackTrace", Arrays.toString(ex.getStackTrace()));
        model.addAttribute("title","General Error");
        return "error";
    }
}
