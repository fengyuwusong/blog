package cn.niriqiang.blog.service;

import cn.niriqiang.blog.domain.Tag;
import cn.niriqiang.blog.domain.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fengyuwusong on 2017/9/26 1:00.
 */
@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public int insertTag(Tag tag) {
        return tagRepository.insertTag(tag);
    }

}
