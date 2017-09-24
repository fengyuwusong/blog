package cn.niriqiang.blog.domian;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by fengyuwusong on 2017/9/23 23:17.
 */
@Data
@Entity
public class ArticleTag {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true, nullable = false, length = 20)
    private String tagName;
}
