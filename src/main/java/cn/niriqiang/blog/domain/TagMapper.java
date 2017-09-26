package cn.niriqiang.blog.domain;


import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * TagMapper
 * Created by fengyuwusong on 2017/9/26 0:51.
 */

@Mapper
@Repository
public interface TagMapper {
    /**
     * 添加一个Tag
     *
     * @param tag
     *
     * @return int 主键id
     */
    int insert(Tag tag);

    /**
     * 更新Tag
     *
     * @param tag
     *
     * @return int 影响行数
     */
    int update(Tag tag);

    /**
     * 查找一个
     *
     * @param id tagId
     *
     * @return Tag
     */
    Tag findOne(int id);

    /**
     * 查找通过tagName
     *
     * @param tagName
     *
     * @return Tag
     */
    Tag findByTagName(String tagName);


    Page<Tag> findAll();

    int delete(int id);
}