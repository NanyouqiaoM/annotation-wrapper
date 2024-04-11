package com.xiyu.wrapper.code.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xiyu.wrapper.code.annotations.Wrapper;
import com.xiyu.wrapper.code.enums.SqlType;
import com.xiyu.wrapper.code.utils.StringFormatUtil;


/**
 * @author caowu
 * @since 2023/5/15
 */
public class KeywordHandle implements Handle<String> {

    public static final String KEYWORD_PREFIX = "Keyword";

    /**
     * 是否处理
     *
     * @param value
     * @return
     */
    @Override
    public boolean condition(String value) {
        return StringUtils.isNotEmpty(value);
    }

    /**
     * 通过字段名称获取column
     *
     * @param fieldName
     * @return
     */
    @Override
    public String column(String fieldName) {
        fieldName = StringFormatUtil.removeSuffix(fieldName, KEYWORD_PREFIX);

        return StringFormatUtil.toUnderlineCase(fieldName);
    }

    @Override
    public String convertType(Object obj) {
        return obj == null ? null : String.valueOf(obj);
    }

    /**
     * @param queryWrapper
     * @param column
     * @param value
     * @param sqlType
     * @return
     */
    @Override
    public <T> QueryWrapper<T> apply(QueryWrapper<T> queryWrapper, String column, String value, SqlType sqlType, Wrapper wrapper) {

        switch (sqlType) {
            case LIKE:
                queryWrapper.like(column, value);
                break;
            case LIKE_LEFT:
                queryWrapper.likeLeft(column, value);
                break;
            case LIKE_RIGHT:
                queryWrapper.likeRight(column, value);
                break;
            default: {
            }
        }

        return queryWrapper;
    }
}
