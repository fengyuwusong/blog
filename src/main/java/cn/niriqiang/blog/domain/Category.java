package cn.niriqiang.blog.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by fengyuwusong on 2017/9/23 23:11.
 */
@Data
public class Category {
    private Integer id;
    @NotNull(message = "类型名称不能为空")
    private String categoryName;
    @NotNull(message = "描述不能为空")
    private String description;
}
