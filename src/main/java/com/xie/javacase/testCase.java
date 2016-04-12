package com.xie.javacase;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/2/23.
 */
public class testCase {
    @Test
    public void censorWordTest() {
        String str = "加.{0,5}她.{0,5}微";
        str = escapeExprSpecialWord(str).replaceAll("\\\\\\.\\\\\\{0\\,5\\\\\\}", "\\.\\*");
        if(Pattern.matches(".*"+str+".*", "加试试她微吧")) {
            System.out.println(str + "匹配");
        }
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtils.isNotBlank(keyword)) {
            String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }
}
