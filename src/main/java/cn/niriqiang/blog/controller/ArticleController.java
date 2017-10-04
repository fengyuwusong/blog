package cn.niriqiang.blog.controller;

import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fengyuwusong on 2017/10/3 12:57.
 */
@Api(value = "前台文章管理接口")
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "查看详情")
    @GetMapping("/read/{id}")
    public Result read(@PathVariable int id) {
        return articleService.read(id);
    }

    @ApiOperation(value = "分页查看所有文章")
    @GetMapping("/{currentPage}")
    public Result findAll(@PathVariable int currentPage) {
        return articleService.findAll(currentPage);
    }

    @ApiOperation(value = "按照类别查询")
    @GetMapping("/category/{currentPage}/{cid}")
    public Result findByCategory(@PathVariable int currentPage, @PathVariable int cid) {
        return articleService.findByCategory(currentPage, cid);
    }

    @ApiOperation(value = "模糊查询")
    @GetMapping("/search/{currentPage}/{keyWord}")
    public Result search(@PathVariable int currentPage, @PathVariable String keyWord) {
        return articleService.search(currentPage, keyWord);
    }

    @ApiOperation(value = "通过tagId查找文章系列")
    @GetMapping("/tag/{currentPage}/{id}")
    public Result findByTagId(@PathVariable int currentPage, @PathVariable int id) {
        return articleService.findByTagId(currentPage, id);
    }



}
