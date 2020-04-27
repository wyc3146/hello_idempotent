package com.wyc.hello.idempotent.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解，标识一个接口需要做幂等校验
 * created on 2020-04-21 16:46
 *
 * @author WangYongcan
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
    // 幂等校验超时时间，默认3分钟
    long timeout() default 1000 * 60 * 3;
}
