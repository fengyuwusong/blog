package cn.niriqiang.blog.domian;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * ArticleRepository 类
 * Created by fengyuwusong on 2017/9/24 16:21.
 */
public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {
}
