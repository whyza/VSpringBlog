package com.whyzaa.vspringblog.requestLimit;


import com.whyzaa.vspringblog.exception.MyException;
import com.whyzaa.vspringblog.util.RedisUtil;
import com.whyzaa.vspringblog.util.ResponseData;
import com.whyzaa.vspringblog.util.ResponseDataUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * date: 2020/7/30 11:31
 *
 * @author whyzaa<br />
 */
@Component
@Order
@Aspect
@Scope
public class LimitAspect {

    private Logger logger = LoggerFactory.getLogger(LimitAspect.class);

    //注入RedisTemplate
    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Resource
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.whyzaa.vspringblog.requestLimit.LimitKey)")
    public void limitService() {}

    /**
     * 这里可以写具体的路径 module包下所有的方法都会调用这个方法  @Around("execution(* com.ehr.module.*.*(..))")
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("limitService()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取拦截的方法相关信息
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;

        Object target = joinPoint.getTarget();
        //为了获取注解信息
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        //获取注解信息
        LimitKey accessLimit = currentMethod.getAnnotation(LimitKey.class);

        // 限流策越，根据package和方法名称组成Key
        String packageName = (methodSignature.getMethod().getDeclaringClass()).getName();
        String functionKey = packageName +"_API>"+ methodSignature.getName();

        //最大次数
        int maxLimit = accessLimit.limit();
        //多长时间的最大次数
        int time = accessLimit.time();
        Integer limit = (Integer) redisUtil.get(functionKey);
        if (limit == null) {
            redisUtil.set(functionKey,1,time);
            return joinPoint.proceed();
        } else if (limit < maxLimit) {
            redisUtil.set(functionKey, (limit + 1), time);
            return joinPoint.proceed();
        } else {
            logger.info("当前 {} 请求超出设定的访问次数，请稍后再试！",functionKey);
            throw new MyException(500,"当前请求超出设定的访问次数，请稍后再试！");
        }
    }
}