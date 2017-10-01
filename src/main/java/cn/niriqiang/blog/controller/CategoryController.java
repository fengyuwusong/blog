package cn.niriqiang.blog.controller;

import cn.niriqiang.blog.domain.Category;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fengyuwusong on 2017/10/1 23:45.
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @ApiOperation("查找全部")
    @GetMapping("/{currentPage}")
    public Result findAll(@PathVariable int currentPage) {
        return categoryService.findAll(currentPage);
    }

    @ApiOperation("查找通过categoryName")
    @GetMapping("/{categoryName}")
    public Result findByCategoryName(@PathVariable String categoryName) {
        return categoryService.findByCategoryName(categoryName);
    }

    @ApiOperation("更新")
    @PutMapping
    public Result update(Category category) {
        return categoryService.updateCategory(category);
    }

    @ApiOperation("删除，如果已经有相关文章的话不能删除")
    @DeleteMapping("{id}")
    public Result delete(@PathVariable int id) {
        return categoryService.delete(id);
    }
}
