package com.wyc.hello.idempotent.feature;

import java.lang.annotation.*;

/**
 * created on 2020-04-27 10:40
 *
 * @author WangYongcan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Documented
public @interface IdempotentKey {
}
