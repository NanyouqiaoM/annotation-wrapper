package com.xiyu.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author caowu
 * @since 2024/2/21
 */
public interface AnnotationWrapper {
    /**
     * 生成QueryWrapper
     * @return QueryWrapper
     */
    default <T> QueryWrapper<T> wrapper() {
        return WrapperGenerator.generateWrapper(this);
    }

    /**
     * 生成 LambdaQueryWrapper
     * @return LambdaQueryWrapper
     */
    default <T> LambdaQueryWrapper<T> lambdaWrapper() {
        QueryWrapper<T> queryWrapper = wrapper();
        return queryWrapper.lambda();
    }
}
