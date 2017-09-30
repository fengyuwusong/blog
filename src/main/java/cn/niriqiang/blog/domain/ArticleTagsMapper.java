package cn.niriqiang.blog.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fengyuwusong on 2017/9/27 21:12.
 */
@Mapper
@Repository
public interface ArticleTagsMapper {
    void insert(ArticleTags articleTags);

    int update(ArticleTags articleTags);

    int delete(ArticleTags articleTags);

    List<ArticleTags> findByArticleId(int id);

    List<ArticleTags> findAll();
}
