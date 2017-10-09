package cn.niriqiang.blog.controller;

import cn.niriqiang.blog.annotation.Admin;
import cn.niriqiang.blog.domain.Config;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fengyuwusong on 2017/10/3 15:09.
 */
@RestController
@Api(value = "配置管理接口")
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @Admin
    @ApiOperation("更新配置信息")
    @PutMapping
    public Result update(@RequestBody Config config) {
        return configService.update(config);
    }

    @ApiOperation("登录")
    @PostMapping
    public Result login(String adminName, String adminPw) {
        return configService.login(adminName, adminPw);
    }


    @ApiOperation("获取博客基本信息")
    @GetMapping
    public Result get() {
        return configService.get();
    }


    @ApiOperation("检测是否已经登录")
    @GetMapping("/isLogin")
    public Result isLogin() {
        return configService.isLogin();
    }
}
