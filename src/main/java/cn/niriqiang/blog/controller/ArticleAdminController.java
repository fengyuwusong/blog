package cn.niriqiang.blog.controller;

import cn.niriqiang.blog.annotation.Admin;
import cn.niriqiang.blog.domain.Article;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.exception.ArticleException;
import cn.niriqiang.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by fengyuwusong on 2017/9/28 9:24.
 */
@Api(value = "后台文章管理接口")
@RestController
@RequestMapping("/admin/article")
public class ArticleAdminController {
    @Autowired
    private ArticleService articleService;

    @Admin
    @ApiOperation(value = "后台查找所有文章")
    @GetMapping("/{currentPage}")
    public Result findAll(@PathVariable int currentPage) {
        return articleService.adminFindAll(currentPage);
    }

    @Admin
    @ApiOperation(value = "添加文章")
    @PostMapping
    public Result insert(@Valid @RequestBody Article article, Errors errors) {

        if (errors.hasErrors()) {
            throw new ArticleException(errors.getFieldError().getDefaultMessage(), -6);
        }

        return articleService.insertArticle(article);
    }


    //todo 有bug
    @Admin
    @ApiOperation(value = "更新文章")
    @PutMapping
    public Result update(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @Admin
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        return articleService.deleteArticle(id);
    }

    @Admin
    @ApiOperation(value = "按照类别查询")
    @GetMapping("/category/{currentPage}/{cid}")
    public Result findByCategory(@PathVariable int currentPage, @PathVariable int cid) {
        return articleService.adminFindByCategory(currentPage, cid);
    }

    @Admin
    @ApiOperation(value = "模糊查询")
    @GetMapping("/search/{currentPage}/{keyWord}")
    public Result search(@PathVariable int currentPage, @PathVariable String keyWord) {
        return articleService.adminSearch(currentPage, keyWord);
    }

    @Admin
    @ApiOperation(value = "查看详情")
    @GetMapping("/read/{id}")
    public Result read(@PathVariable int id) {
        return articleService.adminRead(id);
    }


}
