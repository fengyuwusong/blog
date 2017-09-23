package cn.niriqiang.domian;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by fengyuwusong on 2017/9/23 23:11.
 */
@Entity
@Data
public class ArticleCategory {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull(message = "不能为空")
    @Column(nullable = false, unique = true)
    private String typeName;
    @NotNull(message = "不能为空")
    @Column(nullable = false)
    private String description;
}
