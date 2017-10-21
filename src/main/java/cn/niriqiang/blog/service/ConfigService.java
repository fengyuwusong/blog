package cn.niriqiang.blog.service;

import cn.niriqiang.blog.annotation.Admin;
import cn.niriqiang.blog.domain.Config;
import cn.niriqiang.blog.domain.ConfigMapper;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.enums.ResultEnum;
import cn.niriqiang.blog.exception.LoginException;
import cn.niriqiang.blog.util.CookieUtil;
import cn.niriqiang.blog.util.Md5Util;
import cn.niriqiang.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by fengyuwusong on 2017/10/3 15:01.
 */
@Service
public class ConfigService {
    @Autowired
    private ConfigMapper mapper;

    public Result get() {
        Config config = mapper.get();
        return ResultUtil.success(ResultEnum.OK, config);
    }

    public Result adminGet() {
        Config config = mapper.adminGet();
        return ResultUtil.success(ResultEnum.OK, config);
    }

    public Result update(Config config) {
        int res = mapper.update(config);
        if (res == 1) {
            return ResultUtil.success(ResultEnum.OK, config);
        }
        return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
    }

    public Result login(String adminName, String adminPw) {
        Config config = new Config();
        config.setAdminName(adminName);
        config.setAdminPw(adminPw);
        int res = mapper.login(config);
        if (res == 1) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = attributes.getResponse();
//            存储6小时
            CookieUtil.addCookie(response, "login", Md5Util.getMD5(adminPw), 60 * 60 * 6);
            return ResultUtil.success(ResultEnum.OK, adminName);
        }
        throw new LoginException(ResultEnum.ERROR_NAME_PW);
    }

    public Result click() {
        if (mapper.click() == 1) {
            return ResultUtil.success(ResultEnum.OK, null);
        }
        return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
    }

    @Admin
    public Result isLogin() {
        return ResultUtil.success(ResultEnum.OK, null);
    }


}
