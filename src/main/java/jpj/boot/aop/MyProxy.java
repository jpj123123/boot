package jpj.boot.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: jingpj
 * @Date：creste in 2018/2/11
 */
@Component
@Aspect
public class MyProxy {
    private static Logger logger = LoggerFactory.getLogger(MyProxy.class);
    @Pointcut("execution(* jpj.boot.controller.*.*(..))")
    private void myMethod(){}

    @Around("myMethod()")
    public Object doProxy(ProceedingJoinPoint pjp) throws Throwable {
        //System.out.println(JSON.toJSONString(pjp.getArgs()));
        Object obj = pjp.proceed();

        return obj;
    }
    @Before("myMethod()")
    public void before(JoinPoint pjp){
//        logger.info("q请求数据：{}",pjp.getArgs());
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//        String path_ = request.getContextPath();
//        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
//        logger.info(url);
    }
}
