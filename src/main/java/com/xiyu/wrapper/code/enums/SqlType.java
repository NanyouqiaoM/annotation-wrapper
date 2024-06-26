package com.xiyu.wrapper.code.enums;


import com.xiyu.wrapper.code.handle.*;

/**
 * @author xiyu
 * @since 2023/3/30
 */

public enum SqlType {
    /**
     * sql类型 后续可能会支持更多
     */
    EQ("=", DefaultHandle.class),
    NE("<>", DefaultHandle.class),

    GT(">", DefaultHandle.class),
    GE(">=", DefaultHandle.class),

    LT("<", DefaultHandle.class),
    LE("<=", DefaultHandle.class),

    IN("IN", CollectionHandle.class),
    NOT_IN("NOT IN", CollectionHandle.class),

    LIKE("LIKE '%值%'", KeywordHandle.class),
    LIKE_LEFT("LIKE '%值'", KeywordHandle.class),
    LIKE_RIGHT("LIKE '值%'", KeywordHandle.class),

    IN_SQL("IN SQL", SqlHandle.class),
    NOT_IN_SQL("NOT_IN_SQL", SqlHandle.class),

    IS_NULL("IS_NULL", NullHandle.class),
    IS_NOT_NULL("IS_NOT_NULL", NullHandle.class),

    ORDER_ASC("ORDER BY 字段, ... ASC", OrderHandle.class),
    ORDER_DESC("ORDER BY 字段, ... DESC", OrderHandle.class)
    ;


    /**
     * sql类型 仅作提示
     */
    public final String desc;
    /**
     * handle处理类
     */
    public final Class<? extends Handle<?>> handle;

    SqlType(String desc, Class<? extends Handle<?>> handle) {
        this.desc = desc;
        this.handle = handle;
    }

}
