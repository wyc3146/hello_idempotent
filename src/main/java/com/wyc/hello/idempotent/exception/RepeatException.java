package com.wyc.hello.idempotent.exception;

/**
 * created on 2020-04-26 15:32
 *
 * @author WangYongcan
 */
public class RepeatException extends RuntimeException {
    public RepeatException() {
        super("重复提交");
    }
}
