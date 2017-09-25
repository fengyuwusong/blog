package cn.niriqiang.blog.service;

import cn.niriqiang.BlogApplicationTests;
import cn.niriqiang.blog.domain.Tag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fengyuwusong on 2017/9/26 1:03.
 */
@Transactional
public class TagServiceTest extends BlogApplicationTests {
    @Autowired
    private TagService tagService;

    @Test
    public void insertTag() throws Exception {
        Tag tag = new Tag();
        tag.setTagName("tag1");
        System.out.println(tagService.insertTag(tag));
    }

}