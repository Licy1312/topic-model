package com.shu.service.exceptions;

/**
 * Description：自定义异常类
 * User:Lichangya
 * Date:2017/3/25.
 */
public class DocumentException extends RuntimeException {
    public DocumentException(String message){
        super(message);
    }
}
