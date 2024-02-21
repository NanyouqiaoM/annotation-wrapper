package com.xiyu.wrapper.code.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiyu.wrapper.code.annotations.Wrapper;
import com.xiyu.wrapper.code.enums.SqlType;
import com.xiyu.wrapper.code.utils.StringFormatUtil;

/**
 * @author caowu
 * @since 2024/2/1
 */
public class NullHandle implements Handle<Boolean> {
    public static final String IS_NULL = "IsNull";

    public static final String IS_NOT_NULL = "IsNotNull";

    /**
     * 通过字段名称获取column
     * 默认驼峰转下划线
     *
     * @param fieldName 字段名称
     * @return 对应数据库的字段
     */
    @Override
    public String column(String fieldName) {
        fieldName = StringFormatUtil.removeSuffix(fieldName, IS_NULL);
        fieldName = StringFormatUtil.removeSuffix(fieldName, IS_NOT_NULL);
        return StringFormatUtil.toUnderlineCase(fieldName);
    }

    /**
     * 转换Value类型
     *
     * @param obj 待转换对象
     * @return V
     */
    @Override
    public Boolean convertType(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        return Boolean.valueOf(obj.toString());
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
    public <T> QueryWrapper<T> apply(QueryWrapper<T> queryWrapper, String column, Boolean value, SqlType sqlType, Wrapper wrapper) {


        switch (sqlType) {
            case IS_NULL: {
                if (value) {
                    queryWrapper.isNull(column);
                    break;
                }
                queryWrapper.isNotNull(column);
                break;
            }

            case IS_NOT_NULL: {
                if (value) {
                    queryWrapper.isNotNull(column);
                    break;
                }
                queryWrapper.isNull(column);
                break;
            }
            default: {
            }
        }
        return queryWrapper;
    }
}
