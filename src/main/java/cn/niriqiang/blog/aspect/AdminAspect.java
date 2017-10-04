package cn.niriqiang.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
 * 用于后台接口使用验证
 * Created by fengyuwusong on 2017/10/4 16:07.
 */
@Aspect
@Component
public class AdminAspect {
    private final static Logger logger = LoggerFactory.getLogger(AdminAspect.class);

    //    定义后台接口切入点
    @Pointcut("@annotation(cn.niriqiang.blog.annotation.Admin)")
    public void admin() {
    }

    ;

    @Pointcut("execution(public * cn.niriqiang.blog.controller.*.*(..))")
    public void all() {
    }

    ;


    @Before("admin()")
    public void adminDoBefore(JoinPoint joinPoint) {
//        todo
        logger.info("AdminAspect:检查是否已经登录");
    }


    //    请求前输出常规参数
    @Before("all()")
    private void echoLogger(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        url
        logger.info("url={}", request.getRequestURL());
//        method
        logger.info("method={}", request.getMethod());
//        ip
        logger.info("ip={}", request.getRemoteAddr());
//        类方法 getSignature().getDeclaringTypeName() 类名 getSignature().getName() 类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        参数
        logger.info("args={}", joinPoint.getArgs());
    }

    //    输出返回结果
    @AfterReturning(returning = "object", pointcut = "all()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object.toString());
    }


}
