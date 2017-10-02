package cn.niriqiang.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * Created by fengyuwusong on 2017/9/22 17:11.
 */
@Data
@ApiModel(value = "Article", description = "文章表字段")
public class Article {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "标题")
    @NotNull(message = "标题不能为空")
    private String title;
    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "分类，需要插入时填写")
    private Category category;

    @ApiModelProperty(value = "分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "关联标签")
    private Set<Tag> articleTags;


    @ApiModelProperty(value = "内容")
    @NotNull(message = "内容不能为空")
    private String content;

    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private Date updateTime;
    @ApiModelProperty(value = "状态 -1 删除 0 未发布 1 已发布 2下架", hidden = true)
    private Integer status;
    @ApiModelProperty(value = "作者")
    @NotNull(message = "作者不能为空")
    private String author;

    @ApiModelProperty(value = "点赞", hidden = true)
    private Integer star;

    @ApiModelProperty(value = "阅读量", hidden = true)
    private Integer readNum;

}