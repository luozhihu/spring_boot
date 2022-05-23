package com.example.spring_boot_aop.aspect;

import com.example.spring_boot_aop.annotation.Log;
import com.example.spring_boot_aop.dao.SysLogDao;
import com.example.spring_boot_aop.domain.SysLog;
import com.example.spring_boot_aop.util.HttpContextUtils;
import com.example.spring_boot_aop.util.IPUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private SysLogDao sysLogDao;

    //定义一个切入点
    @Pointcut("@annotation(com.example.spring_boot_aop.annotation.Log)")
    public void pointcut() { }

/*
      * 环绕通知方法的定义格式
      * 1. public
      * 2. 必须有一个返回值，推荐使用Object
      * 3. 方法名称自定义
      * 4. 方法有参数，固定的参数ProceedingJoinPoint
      * @Around：环绕通知
      * 属性：value 切入点表达式
      * 位置 在方法的定义前后执行
      *
      * 特点：
      * 1. 他是功能最强的通知
      * 2. 在目标方法的前后都能增强功能
      * 3. 控制目标方法是否被调用执行
      * 4. 修改原来的目标方法的执行结果，影响最后的调用结果
      * 环绕通知等同于JDK动态代理的InvocationHandler接口
      *
      * 参数：ProceedingJoinPoint 就等同于Method，作用：执行目标方法的
      * 返回值：就是目标方法的执行结果，可以被修改。
      *
      * 环绕通知：经常做事务，在目标方法之前开启事务，执行目标方法，目标方法执行完成后结束事务
      */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            //Method.invoke;Object result = doFirst(); 执行目标方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

	private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            // 注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            sysLog.setParams(params);
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 模拟一个用户名
        sysLog.setUsername("mrbird");
        sysLog.setTime((int) time);
        sysLog.setCreateTime(new Date());
        // 保存系统日志
        sysLogDao.saveSysLog(sysLog);
    }
}