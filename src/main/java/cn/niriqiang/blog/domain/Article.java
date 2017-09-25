package cn.niriqiang.blog.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by fengyuwusong on 2017/9/22 17:11.
 */
@Data
public class Article {

    private Integer id;

    @NotNull(message = "标题不能为空")
    private String title;

    private String secTitle;

    private String description;

    @NotNull(message = "分类不能为空")
    private ArticleCategory articleCategory;

    private List<Tag> articleTags;


    @NotNull(message = "内容不能为空")
    private String content;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    @NotNull(message = "作者不能为空")
    private String author;

    private Integer star;

    private Integer readNum;

}