package cn.niriqiang.blog.util;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 简单分页工具类
 * Created by fengyuwusong on 2017/9/24 16:45.
 */

public class PageUtil {
    //    @Value("${page.size}")
//    todo 配置文件加载
    private static Integer size = 10;

    public static Integer getSize() {
        return size;
    }

    public static void setSize(Integer size) {
        PageUtil.size = size;
    }

    public static Pageable generate(int page, int size, Sort sort) {
        if (sort == null) return new PageRequest(page, size);
        return new PageRequest(page, size, sort);
    }

    public static Pageable generate(int page) {
        return generate(page, size, null);
    }

    public static Pageable generate(int page, Sort sort) {
        return generate(page, size, sort);
    }

}
