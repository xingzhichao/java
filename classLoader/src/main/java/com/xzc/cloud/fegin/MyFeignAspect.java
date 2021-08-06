package com.xzc.cloud.fegin;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @ClassName MyFeignAspect
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/7/29 15:09
 * @Version 1.0
 **/
public class MyFeignAspect {
    @Pointcut("@annotation(com.xzc.cloud.fegin.MyAnnotation)")
    public void annotationPointCut() {

    }
    @Before("annotationPointCut()")
    public void doBefore() {
        //获取当前请求
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(requestAttributes)).getRequest();
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);
        //设置请求头
        requestWrapper.setHeader("Token", "your token value");
    }
}
