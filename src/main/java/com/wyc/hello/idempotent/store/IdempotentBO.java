package com.wyc.hello.idempotent.store;

/**
 * created on 2020-04-27 15:39
 *
 * @author WangYongcan
 */
public class IdempotentBO {

    public IdempotentBO() {}

    public IdempotentBO(String idempotentKey, Long timeout) {
        this.idempotentKey = idempotentKey;
        this.timeout = timeout;
    }

    /**
     * 幂等key
     */
    private String idempotentKey;

    /**
     * 超时时间
     */
    private Long timeout;

    public String getIdempotentKey() {
        return idempotentKey;
    }

    public void setIdempotentKey(String idempotentKey) {
        this.idempotentKey = idempotentKey;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
