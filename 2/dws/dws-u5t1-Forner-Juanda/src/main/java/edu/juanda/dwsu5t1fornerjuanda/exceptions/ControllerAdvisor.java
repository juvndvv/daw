package edu.juanda.dwsu5t1fornerjuanda.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(DataNotFoundException.class)
    public ModelAndView handleNotFoundException(DataNotFoundException ex) {
        ModelAndView mav = new ModelAndView("errors/NotFound");
        mav.addObject("message", ex.getMessage());
        return mav;
    }

    // TODO implementar handleInvalidDataException
    @ExceptionHandler(InvalidDataException.class)
    public ModelAndView handleInvalidDataException(InvalidDataException ex) {
        return null;
    }
}
