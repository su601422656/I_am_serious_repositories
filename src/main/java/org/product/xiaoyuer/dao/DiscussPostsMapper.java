package org.product.xiaoyuer.dao;

import org.apache.ibatis.annotations.Mapper;
import org.product.xiaoyuer.entity.DiscussPost;

import java.util.List;

@Mapper
public interface DiscussPostsMapper {
    List<DiscussPost> findDiscussPosts(int offset, int limit, int userId);

    int findDiscussPostsRows(int userId);



}
