package com.xiyu.wrapper.code.annotations;


import com.xiyu.wrapper.code.enums.SqlType;
import com.xiyu.wrapper.code.handle.AutoHandle;
import com.xiyu.wrapper.code.handle.Handle;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自动构造mybatis-plus queryWrapper注解
 *
 * @author xiyu
 * @since 2023/3/30
 */
@Documented
@Target({FIELD})
@Retention(RUNTIME)
public @interface Wrapper {


    /**
     * 等同于type() 类似spring:@AliasFor("type")
     */
    SqlType value() default SqlType.EQ;

    /**
     * 默认字段名驼峰转下划线
     *
     * @return String
     */
    String column() default "";

    SqlType type() default SqlType.EQ;

    /**
     * 扩展信息 当其他字段无法满足时 自定义Handle时使用。
     *
     * @return String
     */
    String extend() default "";

    /**
     * 采用handle的类型
     *
     * @return
     */
    Class<? extends Handle<?>> handle() default AutoHandle.class;

}
