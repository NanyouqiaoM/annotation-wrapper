package com.xiyu.wrapper.code.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.xiyu.wrapper.code.annotations.Wrapper;
import com.xiyu.wrapper.code.enums.SqlType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author caowu
 * @since 2024/4/9
 */
public class OrderHandle implements Handle<List<String>> {
    public static final String SEPARATOR = ",";
    /**
     * @param fieldName 字段名称
     * @return 对应数据库的字段
     */
    @Override
    public String column(String fieldName) {
        return "";
    }

    /**
     * 是否处理
     * false 不处理
     * true处理
     *
     * @param value
     * @return
     */
    @Override
    public boolean condition(List<String> value) {
        return CollectionUtils.isNotEmpty(value);
    }

    /**
     * 转换Value类型
     *
     * @param obj 待转换对象
     * @return V
     */
    @Override
    public List<String> convertType(Object obj) {
        if (obj == null) {
            return Collections.emptyList();
        }
        if (List.class.isAssignableFrom(obj.getClass())) {
            List<?> list = (List<?>) obj;
            if (list.isEmpty()) {
                return Collections.emptyList();
            }
            return list.stream().map(Object::toString).collect(Collectors.toList());
        }
        if (obj instanceof String) {
            String str= (String) obj;
            return Stream.of(str.split(SEPARATOR))
                    .collect(Collectors.toList());
        }
        return Collections.singletonList(obj.toString());
    }


    /**
     * 处理queryWrapper
     *
     * @param queryWrapper queryWrapper
     * @param column       列名
     * @param value        值
     * @param sqlType      sql类型
     * @param wrapper
     * @return queryWrapper
     */
    @Override
    public <T> QueryWrapper<T> apply(QueryWrapper<T> queryWrapper, String column, List<String> value, SqlType sqlType, Wrapper wrapper) {
        switch (sqlType) {
            case ORDER_ASC:
                queryWrapper.orderByAsc(value);
                break;
            case ORDER_DESC:
                queryWrapper.orderByDesc(value);
                break;
            default: {
            }
        }
        return queryWrapper;
    }
}
