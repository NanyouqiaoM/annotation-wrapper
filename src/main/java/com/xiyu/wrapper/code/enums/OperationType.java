package com.xiyu.wrapper.code.enums;

/**
 * @author caowu
 * @since 2024/5/30
 */

public enum OperationType {
    /**
     * 操作类型
     */
    insert("新增"),
    remove("删除"),
    select("查询"),
    update("更新"),
    ;

    public final String desc;

    OperationType(String desc) {
        this.desc = desc;
    }
}
