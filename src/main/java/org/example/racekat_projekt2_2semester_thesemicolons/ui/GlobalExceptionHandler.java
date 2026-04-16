package org.example.racekat_projekt2_2semester_thesemicolons.ui;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleValidationException(NoResourceFoundException ex, Model model) {
        //model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        //model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}