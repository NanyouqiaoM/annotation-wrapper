package com.xiyu.wrapper.example;

import com.xiyu.wrapper.code.AnnotationWrapper;
import com.xiyu.wrapper.code.annotations.Wrapper;
import com.xiyu.wrapper.code.enums.SqlType;
import com.xiyu.wrapper.example.entity.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author caowu
 * @since 2024/2/21
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QueryUserParam implements AnnotationWrapper{
    /**
     * 主键ID
     */
    @Wrapper(SqlType.EQ)
    Long id;
    /**
     * 姓名
     */
    @Wrapper(SqlType.LIKE)
    String nameKeyword;
    /**
     * 年龄
     */
    @Wrapper(value = SqlType.LE, column = "age")
    Integer maxAge;
    /**
     * 邮箱
     */
    @Wrapper(value = SqlType.EQ)
    String email;
    /**
     * 角色ID
     */
    @Wrapper(SqlType.IN)
    List<Long> roleIdList;
}
