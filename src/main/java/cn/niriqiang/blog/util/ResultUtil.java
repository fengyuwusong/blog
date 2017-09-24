package cn.niriqiang.blog.util;

import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.enums.ResultEnum;

/**
 * Created by fengyuwusong on 2017/9/24 23:07.
 */
public class ResultUtil {
    public static <T> Result<T> success(ResultEnum resultEnum, T t) {
        return new Result(resultEnum.getCode(), resultEnum.getMessage(), t);
    }

    public static Result error(Integer code, String message) {
        return new Result(code, message);
    }

    public static Result error(ResultEnum resultEnum) {
        return new Result(resultEnum.getCode(), resultEnum.getMessage());
    }
}
