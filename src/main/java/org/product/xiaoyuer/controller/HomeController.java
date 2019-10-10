package org.product.xiaoyuer.controller;

import org.product.xiaoyuer.config.Page;
import org.product.xiaoyuer.entity.DiscussPost;
import org.product.xiaoyuer.entity.User;
import org.product.xiaoyuer.service.DiscussPostsService;
import org.product.xiaoyuer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private DiscussPostsService discussPostsService;
    @Value("${page.current}")
    private int current;
    @Value("${page.limit}")
    private int limit;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String index(Model model , Page page) {
        page.setCurrent(current);
        page.setLimit(limit);
        page.setRows(discussPostsService.findDiscussPostsRows(0));
        page.setPath("/index");
        List<Map<String, Object>> list = new ArrayList<>();
        List<DiscussPost> discussPosts = discussPostsService.findDiscussPosts(page.getOffset(), page.getLimit(), 0);
        for (DiscussPost dis : discussPosts
        ) {
            Map<String, Object> map = new HashMap<>();
            map.put("post",dis);
            User user = userService.findUserById(Integer.parseInt(dis.getUserId()));
            map.put("user", user);
            list.add(map);
        }
        model.addAttribute("discussPosts", list);

        return "/index";
    }
}

