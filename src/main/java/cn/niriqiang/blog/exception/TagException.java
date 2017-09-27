package cn.niriqiang.blog.exception;

import cn.niriqiang.blog.enums.ResultEnum;

/**
 * Created by fengyuwusong on 2017/9/26 21:51.
 */
public class TagException extends RuntimeException {
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public TagException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
