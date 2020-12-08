package indi.haiying.jdbcs.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public class DynamicDataSourceAspect {

    @Pointcut(value = "execution(* indi.haiying.jdbcs.service..*.*(..))")
    public void point() {
    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DS name = method.getAnnotation(DS.class);
        if (name != null) {
            DataSourceHelper.setName(name.value());
        }

        try {
            return joinPoint.proceed();
        } finally {
            DataSourceHelper.clear();
            log.warn("datasource clear");
        }
    }

}
