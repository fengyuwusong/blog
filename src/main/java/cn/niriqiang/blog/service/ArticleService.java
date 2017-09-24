package cn.niriqiang.blog.service;


import cn.niriqiang.blog.domian.Article;
import cn.niriqiang.blog.domian.ArticlePageRepository;
import cn.niriqiang.blog.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * 文章Service
 * Created by fengyuwusong on 2017/9/24 16:27.
 */
@Service
public class ArticleService {
    @Autowired
    private ArticlePageRepository repository;

    /**
     * 分页展示
     *
     * @param page
     *
     * @return
     */
    public Page<Article> findAll(int page) {
        return repository.findAll(PageUtil.generate(page));
    }

    public Article articleAdd(Article article) {
        return repository.save(article);
    }


}
