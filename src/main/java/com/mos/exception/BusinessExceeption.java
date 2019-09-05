package com.mos.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BusinessExceeption extends RuntimeException {
    private String code;

    public BusinessExceeption(String message) {
        super("业务异常"+message);
        code = "401";
    }

    public BusinessExceeption(String message, Throwable cause) {
        super("业务异常"+message, cause);
        code = "401";
    }
}
