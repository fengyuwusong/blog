package cn.niriqiang.blog.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by fengyuwusong on 2017/9/27 14:34.
 */
@Mapper
@Repository
public interface ArticleMapper {

    int insert(Article article);

    Article findByTitle(String title);

    int update(Article article);

    Article findOne(int id);
// todo findAll 分类查询 模糊查询 点赞 阅读
//    List<Article> findAll();
}
