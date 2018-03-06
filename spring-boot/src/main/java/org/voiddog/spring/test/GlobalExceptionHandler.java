package org.voiddog.spring.test;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.voiddog.spring.test.model.Constants;
import org.voiddog.spring.test.model.MessageException;
import org.voiddog.spring.test.model.Response;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Response exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        return Response.error(Constants.STATUS_SYS_ERROR, "系统错误");
    }

    @ExceptionHandler(MessageException.class)
    public Response messageExceptionHandler(HttpServletRequest request, MessageException e){
        return Response.error(Constants.STATUS_MSG_ERROR, e.getMessage());
    }
}
