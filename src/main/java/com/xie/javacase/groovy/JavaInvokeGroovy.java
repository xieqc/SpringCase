package com.xie.javacase.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-11-19
 * Time: 上午10:45
 * To change this template use File | Settings | File Templates.
 */
public class JavaInvokeGroovy {
    private GroovyObject groovyObject;

    /**
     *通过反射,动态载入Groovy类，创建接口实例，调用接口中定义的方法
     */
    @Test
    public void InterfaceInvoke() {
        ClassLoader cl = new JavaInvokeGroovy().getClass().getClassLoader();
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
        try {
            Class groovyClass = groovyCl.parseClass(new File("src/main/java/com/xie/javacase/groovy/GroovyTest.groovy"));
            IGroovyTest gt = (IGroovyTest) groovyClass.newInstance();
            gt.SayHelloWorld();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *通过反射,动态载入Groovy类
     */
    @Test
    public void ClassInvoke() {
        JavaInvokeGroovy dynamicGroovy = new JavaInvokeGroovy();
        Object[] params = {new Integer(2)};
        Object result = dynamicGroovy.invokeScriptMethod("src/main/java/com/xie/javacase/groovy/GroovyTest.groovy", "doit", params);
        System.out.println(result);
//        System.out.println(dynamicGroovy.getProperty("x"));
    }

    public Object getProperty(String name) {
        return groovyObject.getProperty(name);
    }

    public Object invokeScriptMethod(String scriptName, String methodName, Object[] args) {
        ClassLoader parent = this.getClass().getClassLoader();
        GroovyClassLoader loader = new GroovyClassLoader(parent);
        try {
            Class groovyClass = loader.parseClass(new File(scriptName));
            groovyObject = (GroovyObject) groovyClass.newInstance();
            return groovyObject.invokeMethod(methodName, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
