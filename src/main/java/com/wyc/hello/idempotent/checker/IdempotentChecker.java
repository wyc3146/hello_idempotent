package com.wyc.hello.idempotent.checker;

/**
 * created on 2020-04-26 15:48
 *
 * @author WangYongcan
 */
public interface IdempotentChecker {
    /**
     * 校验是否重复
     * @param idempotentKey
     * @return null 不重复
     */
    Object checkRepeat(String idempotentKey);

    Object checkRepeat(String idempotentKey, Long timeout);

    Object checkRepeat(String idempotentKey, String value, Long timeout);
}
