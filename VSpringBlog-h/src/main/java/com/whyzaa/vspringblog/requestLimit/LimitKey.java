package com.whyzaa.vspringblog.requestLimit;

import java.lang.annotation.*;

/**
 * Description:
 * date: 2020/7/30 12:04
 *
 * @author whyzaa<br />
 */
@Inherited
@Documented
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitKey {
    //标识 指定time时间段内的访问次数限制
    int limit() default 5;

    //标识 时间段 秒
    int time() default 60;

}