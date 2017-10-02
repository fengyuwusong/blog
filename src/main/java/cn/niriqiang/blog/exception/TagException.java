package cn.niriqiang.blog.exception;

import cn.niriqiang.blog.enums.ResultEnum;

/**
 * Created by fengyuwusong on 2017/9/26 21:51.
 */
public class TagException extends Exception {

    public TagException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public TagException(String message, Integer code) {
        super(message, code);
    }
}
