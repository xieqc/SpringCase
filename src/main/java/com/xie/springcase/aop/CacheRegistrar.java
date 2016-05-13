package com.xie.springcase.aop;

import com.xie.springcase.annotation.CacheConfig;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/5/12.
 */
@Aspect
@Component
public class CacheRegistrar {
    @Resource(name = "ehcacheManager")
    private CacheManager cacheManager;

    @Around(value = "@annotation(com.xie.springcase.annotation.CacheConfig)&&@annotation(cacheConfig)", argNames = "cacheConfig")
    public Object useCache(ProceedingJoinPoint joinPoint, CacheConfig cacheConfig) throws Throwable {
        boolean useCache = cacheConfig.useCache();
        CacheManager manager = cacheManager;
        String cacheName = cacheConfig.cacheName();
        String cacheKey = buildCacheKey(joinPoint, cacheConfig);
        Object result = null;
        if (useCache) {
            Element element = manager.getCache(cacheName).get(cacheKey);
            if (element!=null) {
                result = element.getValue();
            }
        }
        if (result == null) {
            result = joinPoint.proceed();
            if (useCache) {
                Element element = new Element(cacheKey, result);
                manager.getCache(cacheName).put(element);
            }
        }
        return result;
    }

    protected String buildCacheKey(ProceedingJoinPoint joinPoint, CacheConfig cacheConfig) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        StringBuffer keySb = new StringBuffer("k:").append(signature.toString().hashCode());
        String indexStr = cacheConfig.keyArgsIndex();
        for (String index : indexStr.split(",")) {
            Integer argsIndex = Integer.parseInt(index);
            if (argsIndex > -1 && args != null && argsIndex < args.length) {
                Object obj = args[argsIndex];
                if (obj != null) {
                    keySb.append(":").append(obj.hashCode());
                }
            }
        }
        return keySb.toString();
    }
}
