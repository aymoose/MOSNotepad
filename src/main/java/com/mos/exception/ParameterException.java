package com.mos.exception;


import lombok.Getter;
import lombok.Setter;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-30
 * Time:15:20
 */
@Getter
@Setter
public class ParameterException extends RuntimeException {

    private String code;

    public ParameterException(String message) {
        super("客户端错误" + message);
        code = "400";
    }

    public ParameterException(String message, Throwable cause) {
        super("客户端错误" + message, cause);
        code = "400";
    }
}
