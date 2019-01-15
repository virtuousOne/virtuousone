package com.demo.virtuousone.common;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: 吴宸煊
 * @Date: 2019/1/14
 * @Description: 切面日志
 */
@Aspect
@Component
public class AopLogAspect {
    /*日志*/
    private static final Logger logger = LoggerFactory.getLogger(AopLogAspect.class);
    /* 同步计算每次请求时间*/
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.demo.virtuousone..*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
// 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        //记录下请求内容
//        logger.info("URL: " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD: " + request.getMethod());
////        logger.info("IP: " + request.getRemoteAddr());
//        logger.info("IP: " + GetIp.getIpAddr(request));
//        logger.info("CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        //logger.info("ARGS: " + joinPoint.getArgs().toString());
//        Object[] args=joinPoint.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            logger.info("传入参数:"+args[i]); //输出传入的参数
//        }
//
////        logger.info("ARGS: " + Arrays.toString(joinPoint.getArgs()));
//        logger.info("ContentType: " +  request.getContentType());
//        logger.info("User-Agent: " +  request.getHeader("User-Agent"));
//        Enumeration rnames=request.getParameterNames();
//        StringBuffer str = new StringBuffer();
//        StringBuffer s = new StringBuffer();
//        for (Enumeration e = rnames; e.hasMoreElements() ;) {
//            String thisName=e.nextElement().toString();
//            String thisValue=request.getParameter(thisName);
//            //   logger.info(thisName+"-------"+thisValue);
//            if(!StringUtils.equals("sign",thisName)){
//                str.append(thisName).append("=").append(thisValue).append("&");
//            }
//
//        }
//        str.append("private_key=").append(ApiConstant.SIGN_KEY);
//        String[] strArr = str.toString().split("&");
//        Arrays.sort(strArr);
//        for (String i : strArr) {
//            s.append(i).append("&");
//        }
//
//        if(StringUtils.isNotBlank(request.getParameter("sign"))){
//            if(StringUtils.equals(NumberUtil.bytesToStrHex(Coder.encryptMD5(s.substring(0, s.length() - 1).toString().getBytes())),request.getParameter("sign")) ){
//                request.setAttribute("status",1);
//                //   httpServletRequest.setAttribute("requestStartTime",  System.currentTimeMillis());
//
//            }else {
//                request.setAttribute("status", 0);
//                request.setAttribute("msg", "签名错误");
//                //   httpServletResponse.getWriter().print( new ObjectMapper().configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY,true).writerWithDefaultPrettyPrinter().writeValueAsString(error(data)));
//
//            }
//        }else {
//            request.setAttribute("status", "0");
//            request.setAttribute("msg", "缺少参数");
//        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        //处理完请求,返回内容
//        logger.info("RESPONSE: \n" + new ObjectMapper().configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY,true).setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(ret));
        logger.info("RESPONSE: \n" + new ObjectMapper().configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true).writerWithDefaultPrettyPrinter().writeValueAsString(ret));
        logger.info("SPEND TIME: " + (System.currentTimeMillis() - startTime.get()) + "ms");

    }
}
