package cn.niriqiang.blog.handle;

import cn.niriqiang.blog.dto.Result;
import cn.niriqiang.blog.enums.ResultEnum;
import cn.niriqiang.blog.exception.ArticleException;
import cn.niriqiang.blog.service.ArticleService;
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

    private final static Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof ArticleException) {
            logger.error(e.getMessage());
            ArticleException articleException = (ArticleException) e;
            return ResultUtil.error(articleException.getCode(), articleException.getMessage());
        } else {
            logger.error(ResultEnum.UNKNOW_ERROR.toString());
            return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
        }
    }

}
