package com.wyc.hello.idempotent.aop;

import com.wyc.hello.idempotent.Idempotent;
import com.wyc.hello.idempotent.checker.IdempotentChecker;
import com.wyc.hello.idempotent.exception.RepeatException;
import com.wyc.hello.idempotent.feature.IdempotentFeature;
import com.wyc.hello.idempotent.feature.IdempotentKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * created on 2020-04-21 16:59
 *
 * @author WangYongcan
 */
@Aspect
public class IdempotentAspect {

    @Resource
    private IdempotentChecker idempotentChecker;

    public IdempotentAspect() {
    }

    @Pointcut("@annotation(idempotent)")
    private void idempotentPointcut(Idempotent idempotent) {
    }

    @Around(value = "idempotentPointcut(idempotent)", argNames = "joinPoint,idempotent")
    public Object doAround(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {

        String idempotentKey = getIdempotentKey(joinPoint);
        Object returnValue = null;
        if(idempotentKey != null && checkRepeatKey(idempotentKey)) {
            throw new RepeatException();
        }
        // 获取不到幂等key，不进行幂等操作
        returnValue = joinPoint.proceed();
        return returnValue;
    }

    /**
     * 给定key，判断是否已经处理过
     * @param idempotentKey 校验幂等的key
     * @return
     */
    private boolean checkRepeatKey(String idempotentKey) {
        Object checkResult = idempotentChecker.checkRepeat(idempotentKey);
        return checkResult != null;
    }

    private String getIdempotentKey(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if(arg instanceof IdempotentFeature) {
                return ((IdempotentFeature) arg).idempotentKey();
            }

            for (Field field : arg.getClass().getDeclaredFields()) {
                IdempotentKey idempotentKey = AnnotationUtils.findAnnotation(field, IdempotentKey.class);
                if(idempotentKey != null) {
                    try {
                        field.setAccessible(true);
                        return field.get(arg).toString();
                    } catch (Exception e) {
                        System.err.println("ddd");
                    }
                }
            }
        }

        Method method = ((MethodSignature)(joinPoint.getSignature())).getMethod();
        for (int i=0;i<method.getParameters().length; i++) {
            Parameter parameter = method.getParameters()[i];
            IdempotentKey idempotentKey = AnnotationUtils.findAnnotation(parameter, IdempotentKey.class);
            if(idempotentKey != null) {
                return args[i].toString();
            }
        }

        return null;
    }
}
