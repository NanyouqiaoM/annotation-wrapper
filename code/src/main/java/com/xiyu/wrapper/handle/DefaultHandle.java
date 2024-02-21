package com.xiyu.wrapper.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xiyu.wrapper.annotations.Wrapper;
import com.xiyu.wrapper.enums.SqlType;

import java.util.Objects;

/**
 * @author caowu
 * @since 2023/5/9
 */
public class DefaultHandle implements Handle<Object> {

    /**
     * 是否处理
     *
     * @param value
     * @return
     */
    @Override
    public boolean condition(Object value) {
        if (Objects.isNull(value)) {
            return false;
        }
        if (CharSequence.class.isAssignableFrom(value.getClass())) {
            CharSequence charSequence = (CharSequence) value;
            return StringUtils.isNotEmpty(charSequence);
        }
        return true;
    }

    @Override
    public Object convertType(Object obj) {
        return obj;
    }

    /**
     * @param queryWrapper
     * @param column
     * @param value
     * @return
     */
    @Override
    public <T> QueryWrapper<T> apply(QueryWrapper<T> queryWrapper, String column, Object value, SqlType sqlType, Wrapper wrapper) {
        switch (sqlType) {
            case EQ:
                queryWrapper.eq(column, value);
                break;
            case NE:
                queryWrapper.ne(column, value);
                break;
            case GT:
                queryWrapper.gt(column, value);
                break;
            case GE:
                queryWrapper.ge(column, value);
                break;
            case LT:
                queryWrapper.lt(column, value);
                break;
            case LE:
                queryWrapper.le(column, value);
                break;
            default: {
            }
        }

        return queryWrapper;

    }


}
