package com.hosmos.learning.common.utils.exception;

import com.hosmos.learning.common.utils.ResultBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ControllerException.class)
    @ResponseBody
    public ResultBody doControllerException(ControllerException ce, HttpServletRequest request){
        return ResultBody.fail(ce.getMessage());
    }

    @ExceptionHandler(DaoException.class)
    @ResponseBody
    public ResultBody doDaoException(DaoException de, HttpServletRequest request){
        System.out.println(request.getRequestURI());
        return ResultBody.fail(de.getMessage());
    }
}
