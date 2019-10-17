package org.product.xiaoyuer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.product.xiaoyuer.dao.UserMapper;
import org.product.xiaoyuer.entity.DiscussPost;
import org.product.xiaoyuer.entity.User;
import org.product.xiaoyuer.service.DiscussPostsService;
import org.product.xiaoyuer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = XiaoyuerApplication.class)
public class XiaoyuerApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private DiscussPostsService discussPostsService;
    @Test
    public void contextLoads() {
        User user = userService.findUserById(149);
        User user1 = userService.findUserByEmail("nowcoder146@sina.com");
        User user2 = userService.findUserByUserName("lhh");
        System.out.println(user1.getUsername());
        System.out.println(user2.getId());

    }

    @Test
    public void testInsert() {

        User user = new User();
        user.setActivationCode("test");
        user.setCreateTime(new Date());
        user.setUsername("test");
        user.setEmail("test");
        user.setHeaderUrl("test");
        user.setPassword("test");
        user.setSalt("test");
        user.setStatus(2);
        user.setType(1);
        int status = userService.insertUser(user);
        System.out.println(status);

    }

    @Test
    public void testUpdate() {
        userService.updateStatus(10, 150);
        userService.updatePassword("sssss", 150);
        userService.updateHeader("wwwww:", 150);
    }

    @Test
    public void testDiscussPost() {
        List<DiscussPost> list = new ArrayList<>();
        list = discussPostsService.findDiscussPosts(0, 10, 0);
        int rows = discussPostsService.findDiscussPostsRows(0);
        System.out.println(list);
        System.out.println("总数为：：：：："+rows);
    }

}
