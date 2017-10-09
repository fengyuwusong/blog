package cn.niriqiang.blog.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by fengyuwusong on 2017/10/3 14:49.
 */
@Mapper
@Repository
public interface ConfigMapper {
    //    更新数据
    int update(Config config);

    //    点击量加1
    int click();

    //    登录验证
    int login(Config config);

    //    获取数据
    Config get();

    //    获取密码校验cookie
    Config getAdminPw();
}
