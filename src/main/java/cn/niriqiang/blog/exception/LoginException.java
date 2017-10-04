package cn.niriqiang.blog.exception;

import cn.niriqiang.blog.enums.ResultEnum;

/**
 * Created by fengyuwusong on 2017/10/4 16:01.
 */
public class LoginException extends Exception {
    public LoginException(String message, Integer code) {
        super(message, code);
    }

    public LoginException(ResultEnum resultEnum) {
        super(resultEnum);
    }
}

