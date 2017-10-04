package cn.niriqiang.blog.handle;

import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.enums.ResultEnum;
import cn.niriqiang.blog.exception.ArticleException;
import cn.niriqiang.blog.exception.CategoryException;
import cn.niriqiang.blog.exception.LoginException;
import cn.niriqiang.blog.exception.TagException;
import cn.niriqiang.blog.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fengyuwusong on 2017/9/24 23:17.
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof ArticleException) {
            logger.error("ArticleException:" + e.getMessage());
            ArticleException exception = (ArticleException) e;
            return ResultUtil.error(exception.getCode(), exception.getMessage());
        } else if (e instanceof CategoryException) {
            logger.error("CategoryException:" + e.getMessage());
            CategoryException exception = (CategoryException) e;
            return ResultUtil.error(exception.getCode(), exception.getMessage());
        } else if (e instanceof TagException) {
            logger.error("TagException:" + e.getMessage());
            TagException exception = (TagException) e;
            return ResultUtil.error(exception.getCode(), exception.getMessage());
        } else if (e instanceof LoginException) {
            logger.error("LoginException:" + e.getMessage());
            LoginException exception = (LoginException) e;
            return ResultUtil.error(exception.getCode(), exception.getMessage());
        }
        logger.error("UNKNOW_ERROR:");
        e.printStackTrace();
        return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
    }

}
