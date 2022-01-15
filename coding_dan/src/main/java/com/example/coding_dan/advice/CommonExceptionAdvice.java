package com.example.coding_dan.advice;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

  @ExceptionHandler(Exception.class)
  public String except(HttpServletRequest request, Exception e) {

    log.info("Error: " + e.getMessage());
    log.info("request uri: " + request.getRequestURI());

    return "error page!!";
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handle404(HttpServletRequest request, NoHandlerFoundException e) {

    log.info("Error: " + e.getMessage());
    log.info("request uri: " + request.getRequestURI());

    return "404 NOT FOUND!!";
  }
}
