package cn.niriqiang.blog.service;

import cn.niriqiang.blog.domain.Category;
import cn.niriqiang.blog.domain.CategoryMapper;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.enums.ResultEnum;
import cn.niriqiang.blog.exception.ArticleException;
import cn.niriqiang.blog.exception.CategoryException;
import cn.niriqiang.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyuwusong on 2017/9/27 0:43.
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper mapper;

    @Autowired
    private ArticleService articleService;

    public Result insertCategory(Category category) {
        try {
            findByCategoryName(category.getCategoryName());
        } catch (CategoryException e) {
            mapper.insert(category);
            return ResultUtil.success(ResultEnum.OK, category);
        }
        throw new CategoryException(ResultEnum.ADD_EXITS);
    }

    public Result updateCategory(Category category) {
//        查找id是否存在
        Category res = (Category) findOne(category.getId()).getData();
//        如果CategoryName是需要修改项 查找更改的categoryName是否已经存在
        if (!category.getCategoryName().equals(res.getCategoryName())) {
            try {
                findByCategoryName(category.getCategoryName());
            } catch (CategoryException e) {
                mapper.update(category);
                return ResultUtil.success(ResultEnum.OK, category);
            }
            throw new CategoryException(ResultEnum.ADD_EXITS);
        }
        mapper.update(category);
        return ResultUtil.success(ResultEnum.OK, category);
    }

    public Result findOne(int id) {
        Category category = mapper.findOne(id);
        if (category != null) {
            return ResultUtil.success(ResultEnum.OK, category);
        } else {
            throw new CategoryException(ResultEnum.NOT_FOUND);
        }
    }

    public Result findByCategoryName(String categoryName) {
        Category category = mapper.findByCategoryName(categoryName);
        if (category != null) {
            return ResultUtil.success(ResultEnum.OK, category);
        } else {
            throw new CategoryException(ResultEnum.NOT_FOUND);
        }
    }

    public Result findAll() {
        List<Category> categoryPage = mapper.findAll();
        return ResultUtil.success(ResultEnum.OK, categoryPage);
    }

    public Result delete(int id) {
        findOne(id);
//        查找该分类下的文章是否存在 若存在则不能删除
        try {
            articleService.findByCategory(1, id);
        } catch (ArticleException e) {
            mapper.delete(id);
            return ResultUtil.success(ResultEnum.OK, id);
        }
        throw new CategoryException(ResultEnum.DELETE_FALSE);
    }
}
