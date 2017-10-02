package cn.niriqiang.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by fengyuwusong on 2017/9/23 23:17.
 */
@ApiModel
@Data
public class Tag {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "标签名")
    @NotNull(message = "标签名字不能为空!")
    private String tagName;
}
