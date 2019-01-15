package com.demo.virtuousone.common;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.demo.virtuousone.common.ResponseMsg.error;

/**
 * @Auther: 吴宸煊
 * @Date: 2019/1/14
 * @Description: 统一异常处理
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> defaultErrorHandler(HttpServletRequest req, Exception e) {
        // logger.error(e.getMessage(),e.getCause());
        if (e instanceof MissingServletRequestParameterException) {
            logger.info("异常：" + e.getMessage(), e.getCause());
            return error("请求参数错误：" + ((MissingServletRequestParameterException) e).getParameterName());
        }
        if (e instanceof NumberFormatException) {
//            return error("系统异常：输入数字错误");
            logger.info("异常：" + e.getMessage(), e.getCause());
            return error("数字格式化异常");
        }
        if (e instanceof HttpMessageNotReadableException) {
//            return error("系统异常：输入数字错误");
            logger.info("异常：" + e.getMessage(), e.getCause());
            return error("请求参数错误");
        }
        if (e instanceof JsonMappingException) {
//            return error("系统异常：输入数字错误");
            logger.info("异常：" + e.getMessage(), e.getCause());
            return error("请求参数错误");
        }
        if (e instanceof BindException) {
            return error(((BindException) e).getFieldError().getDefaultMessage());
        }
        logger.info("异常：" + e.getMessage(), e.getCause());
        return error("系统繁忙，请稍后重试");
    }
}
