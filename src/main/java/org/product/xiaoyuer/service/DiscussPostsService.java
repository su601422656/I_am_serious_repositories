package org.product.xiaoyuer.service;

import org.product.xiaoyuer.dao.DiscussPostsMapper;
import org.product.xiaoyuer.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiscussPostsService {
    @Autowired
    private DiscussPostsMapper discussPostsMapper;

    public List<DiscussPost> findDiscussPosts(int offset, int limit, int userId) {
        return discussPostsMapper.findDiscussPosts(offset, limit, userId);
    }

    public int findDiscussPostsRows(int userId) {
        return discussPostsMapper.findDiscussPostsRows(userId);
    }
}
