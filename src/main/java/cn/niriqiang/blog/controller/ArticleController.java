package cn.niriqiang.blog.controller;

import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fengyuwusong on 2017/9/28 9:24.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/{currentPage}")
    public Result findAll(@PathVariable int currentPage) {
        return articleService.findAll(currentPage);
    }


}
