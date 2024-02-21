package com.xiyu.wrapper.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.xiyu.wrapper.annotations.Wrapper;
import com.xiyu.wrapper.enums.SqlType;
import com.xiyu.wrapper.utils.StringFormatUtil;

import java.util.Collection;
import java.util.Collections;

/**
 * @author caowu
 * @since 2023/5/9
 */
public class CollectionHandle implements Handle<Collection<?>> {
    public static final String IN_SUFFIX = "List";

    public static final String NOT_IN_PREFIX = "exclude";

    /**
     * 通过字段名称获取column
     *
     * @param fieldName
     * @return
     */
    @Override
    public String column(String fieldName) {
        fieldName = StringFormatUtil.removePrefix(fieldName, NOT_IN_PREFIX);
        fieldName = StringFormatUtil.removeSuffix(fieldName, IN_SUFFIX);
        return StringFormatUtil.toUnderlineCase(fieldName);
    }

    /**
     * 是否处理
     *
     * @param value
     * @return
     */
    @Override
    public boolean condition(Collection<?> value) {
        return CollectionUtils.isNotEmpty(value);
    }

    @Override
    public Collection<?> convertType(Object obj) {
        if (obj == null || !Collection.class.isAssignableFrom(obj.getClass())) {
            return Collections.emptyList();
        }
        return (Collection<?>) obj;
    }

    /**
     * @param queryWrapper
     * @param column
     * @param value
     * @param sqlType
     * @return
     */
    @Override
    public <T> QueryWrapper<T> apply(QueryWrapper<T> queryWrapper, String column, Collection<?> value, SqlType sqlType, Wrapper wrapper) {
        switch (sqlType) {
            case IN:
                queryWrapper.in(column, value);
                break;
            case NOT_IN:
                queryWrapper.notIn(column, value);
                break;
            default: {
            }
        }
        return queryWrapper;
    }

}
