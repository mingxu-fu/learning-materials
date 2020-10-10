package com.yingxue.lesson.exception;

/**
 * @ClassName: BusinessException
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public class BusinessException extends RuntimeException{

    private final int messageCode;

    private final String messageDefault;

    public BusinessException(int messageCode, String message ) {
        super(message);
        this.messageCode = messageCode;
        this.messageDefault = message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public String getMessageDefault() {
        return messageDefault;
    }
}
