package cn.niriqiang.blog.service;

import cn.niriqiang.BlogApplicationTests;
import cn.niriqiang.blog.domian.Article;
import cn.niriqiang.blog.domian.ArticleCategoryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 * Created by fengyuwusong on 2017/9/24 17:00.
 */
public class ArticleServiceTest extends BlogApplicationTests {
    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleCategoryRepository categoryRepository;
    @Test
    public void findAll() throws Exception {
        Page<Article> articles = articleService.findAll(1);
        System.out.println(articles.getContent());
    }

    @Test
    public void articleAdd() throws Exception {
        Article article = new Article();

        article.setArticleCategory(articleService.findAll(0).getContent().get(0).getArticleCategory());
        article.setAuthor("风雨雾凇");
        article.setContent("测试~~~~~~~~~~~~~~~~");
        article.setDescription("测试description~~~~~~~~~~");
        article.setTitle("测试标题~~~~~~~~");
        article.setSecTitle("测试副标题~~~~~~~~");
        article.setUpdateTime(new Date());
        article.setCreateTime(new Date());
        articleService.articleAdd(article);
    }

}