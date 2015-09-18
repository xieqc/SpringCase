package com.xie.javacase.programStruct.controlFlow;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-11-3
 * Time: 上午10:18
 * To change this template use File | Settings | File Templates.
 */
public class MultipleSelections {
    @Test
    public void switchTest() {
        //byte、short、char能隐式转换为int基本类型或Integer包装类型
        char c = '1';
        short s = 1;
        long l = 1; //不能隐式转换成为int,ERROR
        switch(s) {
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            default:
                System.out.println("other:"+(int)c);
                break;
        }
    }

//    @Test
    public void switchStringTest() {
        //jdk1.7以后,switch允许对字符串进行判断
        String str = "2";
        switch(str) {
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("2");
                break;
            case "3":
                System.out.println("3");
                break;
            default:
                System.out.println("other");
                break;
        }
    }
}
