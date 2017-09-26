package cn.niriqiang.blog.enums;

/**
 * Created by fengyuwusong on 2017/9/24 16:29.
 */
public enum ResultEnum {
    OK(200, "成功"),
    NOT_BLANK(0, "不能为空"),
    UNKNOW_ERROR(-1, "未知错误"),
    NOT_FOUND(-2, "目标未找到"),
    ADD_EXITS(-3, "目标已存在");

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResultEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    Integer code;
    String message;
}
