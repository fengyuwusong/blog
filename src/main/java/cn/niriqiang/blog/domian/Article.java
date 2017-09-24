package cn.niriqiang.blog.domian;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by fengyuwusong on 2017/9/22 17:11.
 */
@Data
@Entity
public class Article {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "标题不能为空")
    @Column(nullable = false, name = "title", length = 50)
    private String title;

    @Column(name = "secTile", length = 100)
    private String secTitle;

    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "分类不能为空")
    @JoinColumn(name = "categoryId", nullable = false)
    @ManyToOne(targetEntity = ArticleCategory.class, cascade = {CascadeType.PERSIST})
    private ArticleCategory articleCategory;

    @Column(nullable = false)
    @OneToMany(targetEntity = ArticleTag.class, cascade = {CascadeType.PERSIST})
    private List<ArticleTag> articleTags;

    @NotNull(message = "内容不能为空")
    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "createTime", columnDefinition = "datetime default now()")
    private Date createTime;

    @Column(name = "updateTime", columnDefinition = "datetime default now()")
    private Date updateTime;

    @Column(name = "status", columnDefinition = "int default 0 comment '0 未发布 1 已发布 -1 删除 2 转载'")
    private Integer status;

    @NotNull(message = "作者不能为空")
    @Column(nullable = false, name = "author", length = 20)
    private String author;

    @Column(name = "star", columnDefinition = "int default 0")
    private Integer star;

    @Column(name = "readNum", columnDefinition = "int default 0")
    private Integer readNum;

}