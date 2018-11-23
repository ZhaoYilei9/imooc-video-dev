package com.imooc.enums;

public enum ErrorMsgEnums {
    REGIST_SUCCESS(1,"注册成功"),
    USER_EXIST(2,"用户名已存在"),
    USER_NOT_NULL(3,"用户名或密码不能为空"),
    USERNAME_NOT_SUIT_PASSWORD(4,"用户名或密码不正确")
    ;

    public final int status;
    public final String msg;

    ErrorMsgEnums(int status,String msg){
        this.status = status;
        this.msg = msg;
    }

}
