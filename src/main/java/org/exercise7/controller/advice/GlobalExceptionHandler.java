package org.exercise7.controller.advice;

import org.exercise7.model.exceptions.DomainException;
import org.exercise7.model.exceptions.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException ex, Model model) {
        logger.warn("Recurso no encontrado: {}", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/404";
    }

    @ExceptionHandler(DomainException.class)
    public String handleDomainException(DomainException ex, Model model) {
        logger.warn("Violación de regla de negocio: {}", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/business-error";
    }

}