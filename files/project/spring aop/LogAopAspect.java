package com.gomain.makeseal.aop;

import com.gomain.makeseal.bean.Results;
import com.gomain.makeseal.common.ErrorCode;
import com.gomain.makeseal.common.ParamsUtil;
import com.gomain.makeseal.log.service.LogService;
import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 切面类
 * 对返回给前端的对象进行检查：非Results类对象的进行分装：异常/未格式化结果
 * 对请求参数的log日志记录
 *
 * @author WangChengyu
 * 2018/5/31 11:13
 */
@Aspect
@Component
@Order(10)
public class LogAopAspect {
    static Logger log = LoggerFactory.getLogger(LogAopAspect.class);

    @Autowired
    LogService logService;


    @Pointcut("execution(public * com.gomain.makeseal..*Controller.*(..)) && !execution(* com.gomain.makeseal.log..*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        //        System.out.println("方法的进入前 : ");
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容  
        //        System.out.println("方法的返回值 : " + ret);
    }

    //后置异常通知  
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp) {
        //        System.out.println("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行  
    @After("webLog()")
    public void after(JoinPoint jp) {
        // System.out.println("方法最后执行.....");
    }

    //环绕通知,环绕增强，相当于MethodInterceptor  
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {

        // 接收到请求，记录请求内容  
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容  
        String uri = request.getRequestURI();
        String module = getModuleName(uri);
        log.info("模块 ：" + module + "\t uuid = " + request.getAttribute("uuid"));
        // -- 获得操作类型和请求参数
        String optType = getOptType(uri);
        String optCtx = getRequestBody2JsonString(pjp);
        optCtx = (null == optCtx) ? getFormReq2JsonString(request) : optCtx;
        String uid = request.getParameter("userId");
//        if (ParamsUtil.checkNull(uid)) uid = null;
        try {
            Object o = pjp.proceed();
            if (o instanceof Results) {//根据results的code确定操作执行结果并记录 1
                if (((Results) o).getCode() == ErrorCode.SUCCESS) {
                    try {
                        logService.saveLog(request.getAttribute("uuid").toString(), uid, optType,
                                module, optCtx, ((Results) o).getCode() + "", "0", "1");
                    } catch (Exception e) {// 忽略记错的日志
                        log.warn("日志记录失败", e.getMessage());
                    }
                } else {
                    try {
                        logService.saveLog(request.getAttribute("uuid").toString(), uid, optType,
                                module, optCtx, "1", ((Results) o).getCode() + "", "2");
                    } catch (Exception e) {// 忽略记错的日志
                        log.warn("日志记录失败", e.getMessage());
                    }
                }
            } else {//未格式化的返回结果比results的要严重些 2
                try {
                    logService.saveLog(request.getAttribute("uuid").toString(), uid, optType,
                            module, optCtx, "1", ErrorCode.MS_EXEC_FAILURE + "", "3");
                } catch (Exception e) {// 忽略记错的日志
                    log.warn("日志记录失败", e.getCause().getMessage());
                }
            }
            return o;
        } catch (Throwable e) {//异常的错误级别更高 3
            try {
                logService.saveLog(request.getAttribute("uuid").toString(), uid, optType,
                        module, optCtx, "1", ErrorCode.MS_L_EXCEPTION + "", "4");
            } catch (Exception e1) {// 忽略记错的日志
                log.warn("日志记录失败", e1.getMessage());
            }
            return e;//记录后将异常返回给外层aop
        }
    }


    /**
     * 匹配操作类型
     */
    private String getOptType(String uri) {
        // uri匹配模块名
        String[] spUri = uri.split("/");
        if (spUri.length < 3) return null;
        String flag = spUri[2];
        String rtn;
        if (flag.startsWith("query") || flag.startsWith("find")) {
            rtn = "00";
        } else if (flag.startsWith("ad") || flag.startsWith("in")) {
            rtn = "01";// 增加
        } else if (flag.startsWith("upd") || flag.startsWith("mod")) {
            rtn = "02";// 更新
        } else if (flag.startsWith("del")) {
            rtn = "03";// 删除
        } else if (flag.startsWith("count") || flag.startsWith("statis")) {
            rtn = "05";// 统计|计数
        } else {
            rtn = "99";// 其它
        }
        return rtn;
    }


    /**
     * 匹配模块名
     */
    private String getModuleName(String uri) {
        // uri匹配模块名
        String[] spUri = uri.split("/");
        if (spUri.length <= 0) return null;
        String rtn;
        switch (spUri[1]) {// 以下罗列模块名称
            case "apply":
                rtn = "印章申请管理";
                break;
            case "mutual":
                rtn = "信息交互中心";
                break;
            case "module":
                rtn = "印模管理";
                break;
            case "query":
                rtn = "查询";
                break;
            case "queryStatistics":
                rtn = "查询统计";
                break;
            case "seal":
                rtn = "印章管理";
                break;
            case "trafficParam":
                rtn = "业务参数管理";
                break;
            case "log":
                rtn = "操作日志";
                break;
            case "msp":
                rtn = "制作点信息";
                break;
            case "files":
                rtn = "文件上传";
                break;
            case "systemInit":
                rtn = "系统初始化";
                break;
            case "num":
                rtn = "发号";
                break;
            case "pubMonitor":
                rtn = "发布任务监控";
                break;
            default:
                rtn = "其它模块";
                break;
        }
        return rtn;
    }

    /**
     * 获得form表单数据
     */
    private String getFormReq2JsonString(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return JSONObject.fromObject(map).toString();
    }

    /**
     * 从args中读取第一个参数，一般为@RequestBody
     */
    private String getRequestBody2JsonString(ProceedingJoinPoint pjp) {
        log.info("test mark  ------------ ");
        Object[] args = pjp.getArgs();
        if (args.length > 0) {
            log.info(JSONObject.fromObject(args[0]).toString());
            return JSONObject.fromObject(args[0]).toString();
        }
        return null;
    }

}
