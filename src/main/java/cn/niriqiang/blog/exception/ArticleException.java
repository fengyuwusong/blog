package cn.niriqiang.blog.exception;

import cn.niriqiang.blog.enums.ResultEnum;

/**
 * Created by fengyuwusong on 2017/9/24 22:55.
 */
public class ArticleException extends Exception {

    public ArticleException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public ArticleException(String message, Integer code) {
        super(message, code);
    }

}
