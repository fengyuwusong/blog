package cn.niriqiang.blog.domain;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    List<Category> findAll();
}
