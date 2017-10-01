package cn.niriqiang.blog.domain;

import com.github.pagehelper.Page;
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

    Page<Article> findAll();

    int delete(int id);

    Page<Article> findByCategory(int cid);

    Page<Article> search(String keyWord);

    int up(int id);

    int read(int id);


}
