package cn.niriqiang.blog.service;

import cn.niriqiang.blog.domain.Category;
import cn.niriqiang.blog.domain.CategoryMapper;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.enums.ResultEnum;
import cn.niriqiang.blog.exception.CategoryException;
import cn.niriqiang.blog.util.ResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by fengyuwusong on 2017/9/27 0:43.
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper mapper;
    @Value("${page.size}")
    private int pageSize;

    Result insertCategory(Category category) {
        try {
            findByCategoryName(category.getCategoryName());
        } catch (CategoryException e) {
            category.setId(mapper.insert(category));
            return ResultUtil.success(ResultEnum.OK, category);
        }
        throw new CategoryException(ResultEnum.ADD_EXITS);
    }

    Result updateCategory(Category category) {
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

    Result findOne(int id) {
        Category category = mapper.findOne(id);
        if (category != null) {
            return ResultUtil.success(ResultEnum.OK, category);
        } else {
            throw new CategoryException(ResultEnum.NOT_FOUND);
        }
    }

    Result findByCategoryName(String categoryName) {
        Category category = mapper.findByCategoryName(categoryName);
        if (category != null) {
            return ResultUtil.success(ResultEnum.OK, category);
        } else {
            throw new CategoryException(ResultEnum.NOT_FOUND);
        }
    }

    Result findAll(int currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        Page<Category> categoryPage = mapper.findAll();
        return ResultUtil.success(ResultEnum.OK, categoryPage);
    }

    Result delete(int id) {
        findOne(id);
        mapper.delete(id);
        return ResultUtil.success(ResultEnum.OK, id);
    }
}
