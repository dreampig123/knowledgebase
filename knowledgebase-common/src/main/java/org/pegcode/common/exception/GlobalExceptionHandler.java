package org.pegcode.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.pegcode.common.entity.response.ErrorResponse;
import org.pegcode.common.entity.response.ResponseEntity;
import org.pegcode.common.enums.ExceptionEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获全局异常，处理所有不可知的异常
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception e, HttpServletRequest request) {
        log.error("出现未知异常 -> ", e);
        ErrorResponse response = new ErrorResponse();
        response.setCode(ExceptionEnum.ERROR.code);
        response.setBody(e.getMessage());
        response.setRequestUrl(request.getRequestURL().toString());
        response.setException(e.getClass().getName());
        return response;
    }

    /**
     * 捕获空指针异常
     */
    /*@ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity handleNullPointerException(NullPointerException e, HttpServletRequest request) {
        log.error("出现空指针异常 -> ", e);
        ErrorResponse response = new ErrorResponse();
        response.setCode("NULL_POINTER_ERROR");
        response.setBody("空指针异常");
        response.setRequestUrl(request.getRequestURL().toString());
        response.setException(e.getClass().getName());
        return response;
    }*/

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("出现业务异常 -> ", e);
        ErrorResponse response = new ErrorResponse();
        response.setCode(e.getCode());
        response.setBody(e.getMessage());
        response.setRequestUrl(request.getRequestURL().toString());
        response.setException(e.getClass().getName());
        return response;
    }
}
