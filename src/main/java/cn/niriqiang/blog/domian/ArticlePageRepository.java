package cn.niriqiang.blog.domian;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * ArticlePageRepository 类
 * Created by fengyuwusong on 2017/9/24 16:21.
 */
public interface ArticlePageRepository extends PagingAndSortingRepository<Article, Integer> {
    public Page<Article> findAll(Pageable pageable);
}
