package com.mysoft.alpha.exception;

import com.mysoft.alpha.result.Result;
import com.mysoft.alpha.result.ResultFactory;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Global exception handler.
 *
 */
@ControllerAdvice
@ResponseBody
public class DefaultExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);
	
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        String message = "系统错误";

        if (e instanceof IllegalArgumentException) {
            message = "传入了错误的参数";
        }

        if (e instanceof MethodArgumentNotValidException) {
            message = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        }

        if (e instanceof UnauthorizedException) {
            message = "权限认证失败";
        }
        
        if (e instanceof CustomException) {
        	CustomException customException = (CustomException) e;
        	message = customException.getMsg();
        }
        
        log.error(message,e);
        return ResultFactory.buildFailResult(message);
    }
}
