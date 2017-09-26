package cn.niriqiang.blog.domain;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by fengyuwusong on 2017/9/26 23:17.
 */
@Mapper
@Repository
public interface CategoryMapper {
    Category findByCategoryName(String categoryName);

    Category findOne(int id);

    int update(Category category);

    int insert(Category category);

    int delete(int id);

    Page<Category> findAll();
}
