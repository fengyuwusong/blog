package cn.niriqiang.blog.exception;

import cn.niriqiang.blog.enums.ResultEnum;

/**
 * Created by fengyuwusong on 2017/9/27 0:46.
 */
public class CategoryException extends Exception {
    public CategoryException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public CategoryException(String message, Integer code) {
        super(message, code);
    }

}
