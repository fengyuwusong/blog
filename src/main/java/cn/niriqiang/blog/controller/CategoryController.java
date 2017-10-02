package cn.niriqiang.blog.controller;

import cn.niriqiang.blog.domain.Category;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.exception.CategoryException;
import cn.niriqiang.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by fengyuwusong on 2017/10/1 23:45.
 */
@Api(value = "分类管理接口")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @ApiOperation("查找全部")
    @GetMapping("/{currentPage}")
    public Result findAll(@PathVariable int currentPage) {
        return categoryService.findAll(currentPage);
    }

//    @ApiOperation("模糊查找")
//    @GetMapping("/search/{categoryName}")
//    public Result findByCategoryName(@PathVariable String categoryName) {
//        return categoryService.findByCategoryName(categoryName);
//    }

    @ApiOperation("更新")
    @PutMapping
    public Result update(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @ApiOperation("删除，如果已经有相关文章的话不能删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        return categoryService.delete(id);
    }


    @ApiOperation("添加")
    @PostMapping
    public Result insert(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            throw new CategoryException(errors.getFieldError().getDefaultMessage(), -6);
        }
        return categoryService.insertCategory(category);
    }
}
