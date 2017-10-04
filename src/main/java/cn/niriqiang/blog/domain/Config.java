package cn.niriqiang.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by fengyuwusong on 2017/10/3 14:43.
 */
@Data
@ApiModel(value = "Config", description = "配置bean")
public class Config {
    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "后台用户名")
    private String adminName;

    @ApiModelProperty(value = "后台密码")
    private String adminPw;

    @ApiModelProperty(value = "博客名")
    private String blogName;

    @ApiModelProperty(value = "底栏")
    private String footer;

    @ApiModelProperty(value = "博客头像")
    private String blogPid;

    @ApiModelProperty(value = "博客标题")
    private String title;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "git名称")
    private String git;

    @ApiModelProperty(value = "gitUrl")
    private String gitUrl;
}
