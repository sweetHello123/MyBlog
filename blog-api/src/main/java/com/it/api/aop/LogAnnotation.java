package com.it.api.aop;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/16 4:22
 */

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author china wu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";

    String operation() default "";

}
