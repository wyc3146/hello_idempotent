package com.wyc.hello.idempotent.feature;

/**
 * 可以实现这个接口给定幂等的key，可是检验是否是同一个请求的唯一标准
 * created on 2020-04-21 16:50
 *
 * @author WangYongcan
 */
public interface IdempotentFeature {
    /**
     * 需要实现这个方法
     * 可以通过产生一个业务不相关的key，如 UUID.randomUUID().toString()
     * 也可以用业务相关的参数，比如有个请求中有个参数 serialNumber 可以唯一标识这个请求，则可以直接返回这个字段
     * @return
     */
    String idempotentKey();

    default void hehe(Integer type, @IdempotentKey String key, String value) {
        System.out.println();
    }

    public static void main(String[] args) {
        IdempotentFeature feature = new IdempotentFeature() {
            @Override
            public String idempotentKey() {
                return "www";
            }
            @Override
            public void hehe(Integer type,@IdempotentKey String key, String value) {
                System.out.println();
            }
        };

        System.out.println();
    }
}
