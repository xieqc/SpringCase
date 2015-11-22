package com.xie.springcase.aop;

import com.xie.springcase.annotation.DataLog;
import com.xie.springcase.util.JackSonParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Created by xieqinchao on 15-11-20.
 */
@Aspect
public class LogAspect {
    private static Logger logger = Logger.getLogger(LogAspect.class);

    private static final String integerClass = "class java.lang.Integer";
    private static final String longClass = "class java.lang.Long";
    private static final String shortClass = "class java.lang.Short";
    private static final String doubleClass = "class java.lang.Double";
    private static final String bolleanClass = "class java.lang.Boolean";
    private static final String dateClass = "class java.util.Date";
    private static final String bigDecimalClass = "class java.math.BigDecimal";
    private static final String stringClass = "class java.lang.String";

    public void doDateLog(JoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        Class[] paramClasses = new Class[point.getArgs().length];
        StringBuffer paramVal = new StringBuffer();
        if (point.getArgs().length > 0) {
            for (int i = 0; i < point.getArgs().length; i++) {
                if (point.getArgs()[i] != null) {
                    String argType = point.getArgs()[i].getClass().toString();
                    if (argType.equals(integerClass)) {
                        paramClasses[i] = Integer.class;
                        paramVal.append(",int(" + point.getArgs()[i].toString() + ")");
                    } else if (argType.equals(longClass)) {
                        paramClasses[i] = Long.class;
                        paramVal.append(",long(" + point.getArgs()[i].toString() + ")");
                    } else if (argType.equals(shortClass)) {
                        paramClasses[i] = Short.class;
                        paramVal.append(",short(" + point.getArgs()[i].toString() + ")");
                    } else if (argType.equals(doubleClass)) {
                        paramClasses[i] = Double.class;
                        paramVal.append(",double(" + point.getArgs()[i].toString() + ")");
                    } else if (argType.equals(bolleanClass)) {
                        paramClasses[i] = boolean.class;
                        paramVal.append(",boolean(" + point.getArgs()[i].toString() + ")");
                    } else if (argType.equals(dateClass)) {
                        paramClasses[i] = Date.class;
                        paramVal.append(",Date(" + point.getArgs()[i].toString() + ")");
                    } else if (argType.equals(bigDecimalClass)) {
                        paramClasses[i] = BigDecimal.class;
                        paramVal.append(",BigDecimal(" + point.getArgs()[i].toString() + ")");
                    } else if (argType.equals(stringClass)) {
                        paramClasses[i] = String.class;
                        paramVal.append(",String(" + point.getArgs()[i].toString() + ")");
                    } else if (point.getArgs()[i] == null) {
                        paramClasses[i] = String.class;
                    } else {
                        //参数为Dto或其它自定义对象
                        paramClasses[i] = point.getArgs()[i].getClass();
                        paramVal.append(",").append(paramClasses[i].toString().split(" ")[1]).append(":").append(JackSonParser.beanToJson(point.getArgs()[i]));
                    }
                }
            }
        }
        Class targetClass = point.getTarget().getClass();
        Method method = targetClass.getMethod(methodName, paramClasses);
        if (method != null) {
            boolean hasAnnotation = method.isAnnotationPresent(DataLog.class);
            if (hasAnnotation) {
                DataLog annotation = method.getAnnotation(DataLog.class);
                String methodDescp = annotation.type() + annotation.description();
                System.out.println(methodDescp + "," + paramVal.toString());
            }
        }
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        time = System.currentTimeMillis() - time;
        System.out.println("process time: " + time + " ms");
        return retVal;
    }

    public void doThrowing(JoinPoint jp, Throwable ex) {
        logger.error(jp.getTarget().getClass().getName() + "中的" + jp.getSignature().getName() + "方法抛出" + ex.getClass().getName() + "异常,");
        if (jp.getArgs() != null && jp.getArgs().length > 0) {
            for (int i = 0; i < jp.getArgs().length; i++) {
                System.out.println(jp.getArgs()[i].toString());
                logger.error("参数：--" + jp.getArgs()[i].toString());
            }
        }
    }
}
