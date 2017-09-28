package cn.niriqiang.blog.service;


import cn.niriqiang.blog.domain.*;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.enums.ResultEnum;
import cn.niriqiang.blog.exception.ArticleException;
import cn.niriqiang.blog.exception.TagException;
import cn.niriqiang.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

/**
 * 文章Service
 * Created by fengyuwusong on 2017/9/24 16:27.
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper mapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleTagsMapper articleTagsMapper;
    @Value("${page.size}")
    private int pageSize;

    @Transactional
    Result insertArticle(Article article) {
//        插入未存在的标签
        if (article.getCategoryId() == null) {
            categoryService.insertCategory(article.getCategory());
        }
        try {
            findByTitle(article.getTitle());
        } catch (ArticleException e) {
            if (e.getCode().equals(ResultEnum.NOT_FOUND.getCode())) {
                mapper.insert(article);
                if (article.getArticleTags().size() != 0) {
                    insertArticleTags(article.getId(), article.getArticleTags());
                }
                return ResultUtil.success(ResultEnum.OK, article);
            }
        }
        throw new ArticleException(ResultEnum.ADD_EXITS);
    }

    Result findByTitle(String title) {
        Article article = mapper.findByTitle(title);
        if (article != null) {
            return ResultUtil.success(ResultEnum.OK, article);
        } else {
            throw new ArticleException(ResultEnum.NOT_FOUND);
        }
    }

    Result findOne(int id) {
        Article article = mapper.findOne(id);
        if (article == null) {
            throw new ArticleException(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success(ResultEnum.OK, article);
    }

    Result updateArticle(Article article) {
        Article result = (Article) findOne(article.getId()).getData();
        Set<Tag> before = result.getArticleTags();
        Set<Tag> after = article.getArticleTags();
//        去重复
        after.removeAll(before);
        if (after.size() != 0) {
            insertArticleTags(article.getId(), after);
        }

//        修改修改时间
        article.setUpdateTime(new Date());
        if (mapper.update(article) == 1) {
            return ResultUtil.success(ResultEnum.OK, article);
        }

        return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
    }


    /**
     * 将标签插入表articleTags
     *
     * @param articleId 文章id
     * @param tags      文章标签列表
     */

    private void insertArticleTags(int articleId, Set<Tag> tags) {
        ArticleTags articleTags = new ArticleTags();
        articleTags.setArticleId(articleId);
        try {
            for (Tag tag : tags) {
                tag = (Tag) tagService.insertTag(tag).getData();
                articleTags.setTagId(tag.getId());
                articleTagsMapper.insert(articleTags);
            }
        } catch (Exception e) {
            if (!(e instanceof TagException)) {
                throw e;
            }
        }

    }


}
