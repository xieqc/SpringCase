package com.xie.springcase.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2016/5/12.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheConfig {
    String cacheName();

    String keyArgsIndex() default "-1";

    boolean useCache() default true;

    String cacheType() default "ehcache";
}
