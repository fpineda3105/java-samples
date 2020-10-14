/**
 * @author fpineda
 * @email fernandopineda3105@gmail.com
 * @create date 2020-10-14 11:42:53
 * @modify date 2020-10-14 11:42:53
 * @desc Exception Handler on Rest Operations
 */
package com.fpineda.samples.choreographypattern.adapter.web;

import com.fpineda.samples.choreographypattern.core.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class OrderExceptionController extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> orderNotFoundExceptionHandler(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
