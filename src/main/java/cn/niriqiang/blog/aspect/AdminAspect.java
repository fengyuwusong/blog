package cn.niriqiang.blog.aspect;

import cn.niriqiang.blog.domain.Config;
import cn.niriqiang.blog.domain.ConfigMapper;
import cn.niriqiang.blog.enums.ResultEnum;
import cn.niriqiang.blog.exception.LoginException;
import cn.niriqiang.blog.util.CookieUtil;
import cn.niriqiang.blog.util.Md5Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 用于后台接口使用验证
 * Created by fengyuwusong on 2017/10/4 16:07.
 */
@Aspect
@Component
public class AdminAspect {

    @Autowired
    ConfigMapper mapper;
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
        logger.info("AdminAspect:检查是否已经登录");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie cookie = CookieUtil.getCookieByName(request, "login");
//       和密码的盐值一致
        Config config = mapper.getAdminPw();
        if (cookie != null && cookie.getValue().equals(Md5Util.getMD5(config.getAdminPw()))) {
            logger.info("AdminAspect:已经登录");
        } else {
            logger.warn("还没登录，不能使用!");
            throw new LoginException(ResultEnum.NOT_LOGIN);
        }
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
