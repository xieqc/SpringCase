package com.xie.javacase.memcache.redis.jedis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created with IntelliJ IDEA.
 * User: Xie
 * Date: 14-9-11
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
public class jedisTest {
    @BeforeClass
    public static void initialize() {
    }

    @AfterClass
    public static void end() {
    }

    @Test
    public void test() {
        Jedis jedis = new Jedis("114.215.176.47",6379);
//        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
    }
}
