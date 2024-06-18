package com.xiyu.wrapper.code.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlInjectionUtils;
import com.xiyu.wrapper.code.annotations.Wrapper;
import com.xiyu.wrapper.code.enums.SqlType;

import java.util.List;
import java.util.stream.Collectors;

import static com.baomidou.mybatisplus.core.toolkit.StringPool.*;

/**
 * @author caowu
 * @since 2023/12/27
 */

public class SqlHandle implements Handle<String> {
    /**
     * 是否处理
     * false 不处理
     * true处理
     *
     * @param value
     * @return
     */
    @Override
    public boolean condition(String value) {
        return StringUtils.isNotEmpty(value);
    }

    /**
     * 转换Value类型
     *
     * @param obj 待转换对象
     * @return V
     */
    @Override
    public String convertType(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            return LEFT_BRACKET + list.stream().map(Object::toString).collect(Collectors.joining(COMMA)) + RIGHT_BRACKET;
        }
        return String.valueOf(obj);
    }

    /**
     * 处理queryWrapper
     *
     * @param queryWrapper queryWrapper
     * @param column       列名
     * @param value        值
     * @param sqlType      sql类型
     * @return queryWrapper
     */
    @Override
    public <T> QueryWrapper<T> apply(QueryWrapper<T> queryWrapper, String column, String value, SqlType sqlType, Wrapper wrapper) {
        if (StringUtils.isEmpty(wrapper.extend())) {
            throw new RuntimeException("SqlHandle->apply->wrapper.extend is null");
        }
        if (SqlInjectionUtils.check(value)) {
            throw new RuntimeException("SqlHandle->apply->value is sql injection");
        }
        String sql = String.format(wrapper.extend(), value);
        switch (sqlType) {
            case IN_SQL:
                queryWrapper.inSql(column, sql);
                break;
            case NOT_IN_SQL:
                queryWrapper.notInSql(column, sql);
                break;
            default: {
            }
        }
        return queryWrapper;
    }
}
