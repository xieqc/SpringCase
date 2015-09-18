package com.xie.javacase.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-7-29
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil {
    /**
     * 方法名称:transMapToString
     * 传入参数:map
     * 返回值:String 形如 username:chenziwen,password:1234
     */
    public static String transMapToString(Map map){
        Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append( ":" ).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? "," : "");
        }
        return sb.toString();
    }

    /**
     * 方法名称:transStringToMap
     * 传入参数:mapString 形如 username:chenziwen,password:1234
     * 返回值:Map
     */
    public static Map transStringToMap(String mapString){
        Map map = new HashMap();
        StringTokenizer items;
        for(StringTokenizer entrys = new StringTokenizer(mapString, ",");entrys.hasMoreTokens();
            map.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))
            items = new StringTokenizer(entrys.nextToken(), ":");
        return map;
    }

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("key1","内容1");
        params.put("key2","内容2");
        params.put("key3","内容3");

        System.out.println(transMapToString(params));
    }
}
