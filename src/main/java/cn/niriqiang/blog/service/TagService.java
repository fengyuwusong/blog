package cn.niriqiang.blog.service;

import cn.niriqiang.blog.domain.ArticleTags;
import cn.niriqiang.blog.domain.ArticleTagsMapper;
import cn.niriqiang.blog.domain.Tag;
import cn.niriqiang.blog.domain.TagMapper;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.enums.ResultEnum;
import cn.niriqiang.blog.exception.TagException;
import cn.niriqiang.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyuwusong on 2017/9/26 1:00.
 */
@Service
public class TagService {
    @Autowired
    private TagMapper mapper;
    @Autowired
    private ArticleTagsMapper articleTagsMapper;

    public Result insertTag(Tag tag) {
        if (tag.getId() != null) {
            throw new TagException(ResultEnum.ADD_EXITS);
        }
        try {
            findByTagName(tag.getTagName());
        } catch (TagException e) {
            mapper.insert(tag);
            return ResultUtil.success(ResultEnum.OK, tag);
        }
        throw new TagException(ResultEnum.ADD_EXITS);
    }

    public Result updateTag(Tag tag) {
//        查找id是否存在
        findOne(tag.getId());
//        查找更改的tagName是否已经存在
        try {
            findByTagName(tag.getTagName());
        } catch (TagException e) {
            mapper.update(tag);
            return ResultUtil.success(ResultEnum.OK, tag);
        }
        throw new TagException(ResultEnum.ADD_EXITS);
    }

    public Result findOne(int id) {
        Tag tag = mapper.findOne(id);
        if (tag != null) {
            return ResultUtil.success(ResultEnum.OK, tag);
        } else {
            throw new TagException(ResultEnum.NOT_FOUND);
        }
    }

    public Result findByTagName(String tagName) {
        Tag tag = mapper.findByTagName(tagName);
        if (tag != null) {
            return ResultUtil.success(ResultEnum.OK, tag);
        } else {
            throw new TagException(ResultEnum.NOT_FOUND);
        }
    }

    public Result findAll() {
        List<Tag> tagPage = mapper.findAll();
        return ResultUtil.success(ResultEnum.OK, tagPage);
    }

    public Result delete(int id) {
        findOne(id);
        ArticleTags articleTags = new ArticleTags();
        articleTags.setTagId(id);
//        先删除article_tags表中数据
        articleTagsMapper.delete(articleTags);
        mapper.delete(id);
        return ResultUtil.success(ResultEnum.OK, id);
    }


}
