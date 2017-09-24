package cn.niriqiang.blog.exception;

import cn.niriqiang.blog.enums.ResultEnum;

/**
 * Created by fengyuwusong on 2017/9/24 22:55.
 */
public class ArticleException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ArticleException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
