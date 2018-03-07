package jpj.boot.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/11
 */
@Component
@Aspect
public class MyProxy {
    @Pointcut("execution(* jpj.boot.controller.*.*(..))")
    private void myMethod(){}

    @Around("myMethod()")
    public Object doProxy(ProceedingJoinPoint pjp) throws Throwable {
        //System.out.println(JSON.toJSONString(pjp.getArgs()));
        Object obj = pjp.proceed();

        return obj;
    }
}
