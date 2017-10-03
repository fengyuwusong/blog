package cn.niriqiang.blog.service;

import cn.niriqiang.blog.domain.Config;
import cn.niriqiang.blog.domain.ConfigMapper;
import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.enums.ResultEnum;
import cn.niriqiang.blog.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return ResultUtil.success(ResultEnum.OK, adminName);
        }
        return ResultUtil.error(ResultEnum.ERROR_NAME_PW);
    }

    public Result click() {
        if (mapper.click() == 1) {
            return ResultUtil.success(ResultEnum.OK, null);
        }
        return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
    }

}
