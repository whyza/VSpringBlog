package com.whyzaa.vspringblog.exception;

import com.whyzaa.vspringblog.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * DefaultExceptionHandler:自定义异常处理器
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/12
 */
@Slf4j
@ControllerAdvice   //不指定包默认加了@Controller和@RestController都能控制
//@ControllerAdvice(basePackages ="com.example.demo.controller")
public class DefaultExceptionHandler {

    /**
     * 异常统一自定义处理
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public ResponseData MyException(AuthenticationException e) {
        ResponseData responseData = new ResponseData<>();
        responseData.setCode(401);
        responseData.setMsg(e.getMessage());
        responseData.setData(null);
        return responseData;
    }

    /**
     * 异常统一处理(最后的异常处理)
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseData allException(RuntimeException e) {
        ResponseData responseData = new ResponseData<>();
        responseData.setCode(500);
        responseData.setMsg(e.getMessage());
        responseData.setData(null);
        return responseData;
    }


}
