package com.xie.springcase.aop;

import com.xie.springcase.annotation.DateLog;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieqinchao on 15-11-20.
 */
public class LogAspect {
    public void doSystemLog(JoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        Object[] params = null;
        if(point.getArgs().length>0) {
            params = point.getArgs();
        }
        if (!(methodName.startsWith("set") || methodName.startsWith("get")||methodName.startsWith("query"))){
            Class targetClass = point.getTarget().getClass();
            Method method = null;
            switch(point.getArgs().length) {
                case 0:
                    method = targetClass.getMethod(methodName);
                    break;
                case 1:
                    method = targetClass.getMethod(methodName, params[0].getClass());
                    break;
                case 2:
                    method = targetClass.getMethod(methodName, params[0].getClass(), params[1].getClass());
                    break;
                case 3:
                    method = targetClass.getMethod(methodName, params[0].getClass(), params[1].getClass(), params[2].getClass());
                    break;
            }
            if (method != null) {
                boolean hasAnnotation = method.isAnnotationPresent(DateLog.class);
                if (hasAnnotation) {
                    DateLog annotation = method.getAnnotation(DateLog.class);
                    String methodDescp = annotation.type()+annotation.description();
                    System.out.println(methodDescp);
                }
            }
        }
    }
}
