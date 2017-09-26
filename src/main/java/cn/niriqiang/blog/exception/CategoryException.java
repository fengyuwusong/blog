package cn.niriqiang.blog.exception;

import cn.niriqiang.blog.enums.ResultEnum;

/**
 * Created by fengyuwusong on 2017/9/27 0:46.
 */
public class CategoryException extends RuntimeException {
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CategoryException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
