package cn.niriqiang.blog.controller;

import cn.niriqiang.blog.annotation.Admin;
import cn.niriqiang.blog.domain.Tag;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.exception.TagException;
import cn.niriqiang.blog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by fengyuwusong on 2017/10/1 23:24.
 */
@Api(value = "标签管理接口")
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @Admin
    @ApiOperation(value = "添加新的tag")
    @PostMapping
    public Result insert(@Valid @RequestBody Tag tag, Errors errors) {
        if (errors.hasErrors()) {
            throw new TagException(errors.getFieldError().getDefaultMessage(), -6);
        }
        return tagService.insertTag(tag);
    }

    @Admin
    @ApiOperation("删除tag,该操作将会删除文章与tag的详细，请谨慎操作!")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        return tagService.delete(id);
    }

    @Admin
    @ApiOperation("更新tag")
    @PutMapping
    public Result update(@RequestBody Tag tag) {
        return tagService.updateTag(tag);
    }


    @ApiOperation("查找全部")
    @GetMapping("/{currentPage}")
    public Result findAll(@PathVariable int currentPage) {
        return tagService.findAll(currentPage);
    }

//    @ApiOperation("查找通过名字")
//    @GetMapping("/{tagName}")
//    public Result findByTagName(@PathVariable String tagName) {
//        return tagService.findByTagName(tagName);
//    }
}

