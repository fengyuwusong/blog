package cn.niriqiang.blog.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by fengyuwusong on 2017/9/23 23:17.
 */
@Data
public class Tag {
    private Integer id;
    @NotNull(message = "标签名字不能为空!")
    private String tagName;
}
