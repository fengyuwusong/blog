package cn.niriqiang.blog.service;

import cn.niriqiang.blog.domain.Tag;
import cn.niriqiang.blog.domain.TagMapper;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.enums.ResultEnum;
import cn.niriqiang.blog.exception.TagException;
import cn.niriqiang.blog.util.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by fengyuwusong on 2017/9/26 1:00.
 */
@Service
public class TagService {
    @Autowired
    private TagMapper mapper;
    @Value("${page.size}")
    private int pageSize;

    Result insertTag(Tag tag) {
        try {
            findByTagName(tag.getTagName());
        } catch (TagException e) {
            tag.setId(mapper.insert(tag));
            return ResultUtil.success(ResultEnum.OK, tag);
        }
        throw new TagException(ResultEnum.ADD_EXITS);
    }

    Result updateTag(Tag tag) {
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

    Result findOne(int id) {
        Tag tag = mapper.findOne(id);
        if (tag != null) {
            return ResultUtil.success(ResultEnum.OK, tag);
        } else {
            throw new TagException(ResultEnum.NOT_FOUND);
        }
    }

    Result findByTagName(String tagName) {
        Tag tag = mapper.findByTagName(tagName);
        if (tag != null) {
            return ResultUtil.success(ResultEnum.OK, tag);
        } else {
            throw new TagException(ResultEnum.NOT_FOUND);
        }
    }

    Result findAll(int currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        Page<Tag> tagPage = mapper.findAll();
        return ResultUtil.success(ResultEnum.OK, tagPage);
    }

    Result delete(int id) {
        findOne(id);
        mapper.delete(id);
        return ResultUtil.success(ResultEnum.OK, id);
    }


}
