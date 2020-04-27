# hello-idempotent
通过注解和切面的形式实现接口幂等

支持注解方式指定某个接口做接口幂等校验。
```java
@Idempotent(timeout = 1000 * 60)
@RequestMapping("/haha")
public Greeting2 haha(@RequestBody Greeting2 greeting2) {
    return greeting2;
}
```
@Idempotent标识的方法标识加入幂等校验，可以指定幂等有效时间(默认3分钟)，有效时间内的多次请求只有第一次会被处理。
在此基础上，还需要指定幂等的key。有三种方式指定该方法幂等key。

1. 实现幂等特征接口，实现 IdempotentFeature 接口，String idempotentKey()方法返回幂等key
2. 在方法参数上加入注解 @IdempotentKey ，会以该参数的 toString() 方法返回值作为幂等key
3. 方法参数是一个对象，对象内某个字段被标识了 @IdempotentKey 注解，会调用该字段的 toString() 方法返回值作为幂等key

如果有多种方式指定了幂等key，则优先级为1、2、3

