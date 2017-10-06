package cn.niriqiang.blog.service;

import cn.niriqiang.BlogApplicationTests;
import cn.niriqiang.blog.domain.Tag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by fengyuwusong on 2017/9/26 1:03.
 */
//@Transactional
public class TagServiceTest extends BlogApplicationTests {
    @Autowired
    private TagService tagService;

    @Test
    public void insertTag() throws Exception {
        Tag tag = new Tag();
        tag.setTagName("4444");
        System.out.println(tagService.insertTag(tag).toString());
    }

    @Test
    public void updateTag() throws Exception {
        Tag tag = new Tag();
        tag.setId(2);
        tag.setTagName("tag-1");
        System.out.println(tagService.updateTag(tag).toString());
    }

    @Test
    public void findAll() {
        System.out.println(tagService.findAll().toString());
    }

}