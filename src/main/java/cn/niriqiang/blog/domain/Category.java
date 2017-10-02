package cn.niriqiang.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by fengyuwusong on 2017/9/23 23:11.
 */
@ApiModel
@Data
public class Category {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "分类名")
    @NotNull(message = "类型名称不能为空")
    private String categoryName;
    @ApiModelProperty(value = "分类描述")
    @NotNull(message = "描述不能为空")
    private String description;
}
