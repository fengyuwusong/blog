package cn.niriqiang.blog.service;

import cn.niriqiang.BlogApplicationTests;
import cn.niriqiang.blog.domain.Article;
import cn.niriqiang.blog.domain.Tag;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.enums.ResultEnum;
import cn.niriqiang.blog.exception.ArticleException;
import com.github.pagehelper.Page;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fengyuwusong on 2017/9/27 15:13.
 */
@Transactional
public class ArticleServiceTest extends BlogApplicationTests {
    @Autowired
    private ArticleService articleService;

    @Test
    public void insertArticle() throws Exception {
        Article article = new Article();
        article.setAuthor("风雨雾凇");
        Set<Tag> tags = new HashSet<>();
        Tag tag = new Tag();
        tag.setTagName("333");
        tags.add(tag);
        article.setArticleTags(tags);
        article.setCategoryId(1);
        article.setContent("我是content");
        article.setDescription("我是description");
        article.setTitle("我是title");
        articleService.insertArticle(article);


        try {
            Article article1 = new Article();
            article1.setAuthor("我是王晓佳");
            article1.setArticleTags(null);
            article1.setCategoryId(1);
            article1.setContent("我是content");
            article1.setDescription("我是description");
            article1.setTitle("我是王晓佳");
            articleService.insertArticle(article1);
        } catch (ArticleException e) {
            Assert.assertEquals(e.getCode(), ResultEnum.ADD_EXITS.getCode());
        }
    }

    @Test
    public void findByTitle() throws Exception {
        Result result = articleService.findByTitle("我是王晓佳");
        System.out.println(result);
        try {
            articleService.findByTitle("我是王晓佳11");
        } catch (ArticleException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findOne() throws Exception {
        Result result = articleService.findOne(2);
        System.out.println(result);
        try {
            Result r2 = articleService.findOne(3);
        } catch (ArticleException e) {
            Assert.assertEquals(e.getCode(), ResultEnum.NOT_FOUND.getCode());
        }
    }

    @Test
    public void updateArticle() {
        Article article = new Article();
        article.setId(2);
        article.setAuthor("风雨雾凇");
        Set<Tag> tags = new HashSet<>();
        Tag tag = new Tag();
        tag.setTagName("333");
        tags.add(tag);
        article.setArticleTags(tags);
        article.setCategoryId(6);
        article.setContent("我是content");
        article.setDescription("我是description");
        article.setTitle("我是title");
        articleService.updateArticle(article);
    }

    @Test
    public void findAll() {
        Page<Article> articles = (Page<Article>) articleService.findAll(1).getData();
        System.out.println(articles.getResult().get(0));
    }


    @Test
    public void delete() {
        int id = 1;
        try {
            System.out.println(articleService.deleteArticle(id));
        } catch (ArticleException e) {
            System.out.println(e.getMessage());
        }

        id = 2;
        System.out.println(articleService.deleteArticle(id));
    }

    @Test
    public void findByCategory() {
        int cid = 1;
        Result result = articleService.findByCategory(1, cid);
        Page<Article> articles = (Page<Article>) result.getData();
        List<Article> list = articles.getResult();
        System.out.println(list.get(0));
        try {
            cid = 2;
            result = articleService.findByCategory(1, cid);
        } catch (ArticleException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void search() {

        try {
            Result result = articleService.search(1, "1111");
            Page<Article> articles = (Page<Article>) result.getData();
            List<Article> list = articles.getResult();
            System.out.println(list.get(0));
        } catch (ArticleException e) {
            System.out.println(e.getMessage());
        }
    }

}