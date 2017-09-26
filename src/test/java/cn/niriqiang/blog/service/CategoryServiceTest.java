package cn.niriqiang.blog.service;

import cn.niriqiang.BlogApplicationTests;
import cn.niriqiang.blog.domain.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by fengyuwusong on 2017/9/27 0:53.
 */
public class CategoryServiceTest extends BlogApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void insertCategory() throws Exception {
        Category category = new Category();
        category.setCategoryName("name1");
        category.setDescription("description1");
        System.out.println(categoryService.insertCategory(category).toString());
    }

    @Test
    public void updateCategory() throws Exception {
        Category category = (Category) categoryService.findOne(1).getData();
        category.setDescription("description1-1");
        System.out.println(categoryService.updateCategory(category).toString());
    }

    @Test
    public void findOne() throws Exception {
        System.out.println(categoryService.findOne(1));
    }

    @Test
    public void findByCategoryName() throws Exception {
        System.out.println(categoryService.findByCategoryName("name1"));
    }

    @Test
    public void findAll() throws Exception {
        System.out.println(categoryService.findAll(1));
    }

    @Test
    public void delete() throws Exception {
        System.out.println(categoryService.delete(1));
    }

}