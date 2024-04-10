package com.xiyu.wrapper.code.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiyu.wrapper.code.annotations.Wrapper;
import com.xiyu.wrapper.code.enums.SqlType;
import com.xiyu.wrapper.code.utils.StringFormatUtil;

import java.util.Objects;

/**
 * @author caowu
 * @since 2023/5/8
 */
public interface Handle<V> {
    /**
     * 通过字段名称获取column
     * 默认驼峰转下划线
     *
     * @param fieldName 字段名称
     * @return 对应数据库的字段
     */
    default String column(String fieldName) {
        return StringFormatUtil.toUnderlineCase(fieldName);
    }

    /**
     * 简易判断
     * 是否处理
     * false 不处理
     * true处理
     *
     * @param value
     * @return
     */
    default boolean condition(V value) {
        return Objects.nonNull(value);
    }

    /**
     * 转换Value类型
     *
     * @param obj 待转换对象
     * @return V
     */
    V convertType(Object obj);


    /**
     * 处理queryWrapper
     *
     * @param queryWrapper queryWrapper
     * @param column       列名
     * @param value        值
     * @param sqlType      sql类型
     * @param <T>          泛型
     * @return queryWrapper
     */
    <T> QueryWrapper<T> apply(QueryWrapper<T> queryWrapper, String column, V value, SqlType sqlType, Wrapper wrapper);

}
