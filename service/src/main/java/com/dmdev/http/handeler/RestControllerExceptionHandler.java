package com.dmdev.http.handeler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.dmdev.http.controller.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}
