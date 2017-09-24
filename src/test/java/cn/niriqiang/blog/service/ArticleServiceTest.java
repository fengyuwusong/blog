package cn.niriqiang.blog.service;

import cn.niriqiang.BlogApplicationTests;
import cn.niriqiang.blog.domian.Article;
import cn.niriqiang.blog.domian.ArticleCategory;
import cn.niriqiang.blog.domian.ArticleTag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengyuwusong on 2017/9/24 17:00.
 */
public class ArticleServiceTest extends BlogApplicationTests {
    @Autowired
    ArticleService articleService;

    @Test
    public void findAll() throws Exception {
        Page<Article> articles = articleService.findAll(1);
        articles.toString();
    }

    @Test
    public void articleAdd() throws Exception {
        Article article = new Article();

        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setDescription("测试数据1");
        articleCategory.setTypeName("类型1");

        article.setArticleCategory(articleCategory);

        List<ArticleTag> articleTags = new ArrayList<>();
        ArticleTag articleTag1 = new ArticleTag();
        articleTag1.setTagName("tag1");
        ArticleTag articleTag2 = new ArticleTag();
        articleTag2.setTagName("tag2");
        articleTags.add(articleTag1);
        articleTags.add(articleTag2);

        article.setArticleTags(articleTags);

        article.setAuthor("风雨雾凇");
        article.setContent("测试~~~~~~~~~~~~~~~~");
        article.setDescription("测试description~~~~~~~~~~");
        article.setTitle("测试标题~~~~~~~~");
        article.setSecTitle("测试副标题~~~~~~~~");

        articleService.articleAdd(article);
    }

}