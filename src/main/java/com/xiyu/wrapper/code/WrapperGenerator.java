package com.xiyu.wrapper.code;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xiyu.wrapper.code.annotations.Wrapper;
import com.xiyu.wrapper.code.enums.SqlType;
import com.xiyu.wrapper.code.handle.AutoHandle;
import com.xiyu.wrapper.code.handle.Handle;
import org.apache.ibatis.ognl.OgnlException;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.ibatis.ognl.OgnlRuntime.getPropertyDescriptor;

/**
 * @author caowu
 * @since 2023/5/9
 */
@SuppressWarnings("unchecked")
public class WrapperGenerator {
    private static final ConcurrentHashMap<Class<?>, Handle<?>> HANDLE_MAP = new ConcurrentHashMap<>();

    public static <T, V> QueryWrapper<T> generateWrapper(Object obj) {
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        for (Field field : fields) {
            Wrapper wrapper = field.getAnnotation(Wrapper.class);
            if (wrapper == null) {
                continue;
            }
            PropertyDescriptor fieldPd = null;
            try {
                fieldPd = getPropertyDescriptor(aClass, field.getName());
            } catch (IntrospectionException | OgnlException e) {
                throw new RuntimeException(e);
            }
            if (fieldPd == null) {
                continue;
            }
            SqlType type = wrapper.type();
            if (SqlType.EQ.equals(type)) {
                type = wrapper.value();
            }
            Class<? extends Handle<?>> handleClass = wrapper.handle();
            if (AutoHandle.class.equals(wrapper.handle())) {
                handleClass = type.handle;
            }
            Handle<V> handle = getHandle(handleClass);
            Method readMethod = fieldPd.getReadMethod();
            if (readMethod == null) {
                continue;
            }

            String column = wrapper.column();
            if (StringUtils.isEmpty(column) || StringUtils.isBlank(column)) {
                column = handle.column(field.getName());
            }
            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                readMethod.setAccessible(true);
            }

            try {
                Object valueObj = readMethod.invoke(obj);
                V value = handle.convertType(valueObj);
                if (!handle.condition(value)) {
                    continue;
                }
                handle.apply(queryWrapper, column, value, type, wrapper);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }


        return queryWrapper;
    }

    public static <T> LambdaQueryWrapper<T> generateLambdaWrapper(Object obj) {
        QueryWrapper<T> wrapper = generateWrapper(obj);
        return wrapper.lambda();
    }


    private static <V> Handle<V> getHandle(Class<? extends Handle<?>> handleClass) {
        Handle<?> handle = HANDLE_MAP.get(handleClass);
        if (handle == null) {
            try {
                handle = handleClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            HANDLE_MAP.put(handleClass, handle);
        }
        return (Handle<V>) handle;
    }

}
