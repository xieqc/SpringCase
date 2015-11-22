package com.xie.springcase.annotation;

import java.lang.annotation.*;

/**
 * Created by xieqinchao on 15-11-11.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataLog {
    String type() default "";
    String description() default "";
}
