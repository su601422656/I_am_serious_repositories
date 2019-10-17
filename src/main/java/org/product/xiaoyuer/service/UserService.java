package org.product.xiaoyuer.service;

import org.apache.commons.lang3.StringUtils;
import org.product.xiaoyuer.dao.LoginTicketMappper;
import org.product.xiaoyuer.dao.UserMapper;
import org.product.xiaoyuer.entity.LoginTicket;
import org.product.xiaoyuer.entity.User;
import org.product.xiaoyuer.util.MailClient;
import org.product.xiaoyuer.util.XiaoyuerConstant;
import org.product.xiaoyuer.util.XiaoyuerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
public class UserService implements XiaoyuerConstant {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TemplateEngine templateEngine;


    @Autowired
    private MailClient mailClient;

    @Autowired
    private LoginTicketMappper loginTicketMappper;

    @Value("${xiaoyuer.path.domian}")
    private String domian;

    private String contextPath = "login";


    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }
    public User findUserByEmail(String email){
        return userMapper.findUserByEmail(email);
    }

    public User findUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public int updateHeader(String headerUrl, int id) {
        return userMapper.updateHeader(headerUrl, id);

    }

    public int updateStatus(int status, int id) {
        return userMapper.updateStatus(status, id);
    }

    public int updatePassword(String password, int id) {
        return userMapper.updatePassword(password, id);
    }

    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        if (user == null) {
            throw new  IllegalArgumentException("参数不能为空！");
        }
        if (StringUtils.isBlank((user.getUsername()))) {
            map.put("usernameMsg", "账号不能为空");
            return map;
        }
        if (StringUtils.isBlank((user.getPassword()))) {
            map.put("passwordMsg", "密码不能为空");
            return map;
        }
        if (StringUtils.isBlank((user.getEmail()))) {
            map.put("emailMsg", "邮箱不能为空");
            return map;
        }
//        验证账号
        User u = userMapper.findUserByUserName(user.getUsername());
        if (u != null) {
            map.put("usernameMsg", "账号已经存在");
            return map;
        }
//        验证邮箱
        if (userMapper.findUserByEmail(user.getEmail()) != null) {
            map.put("emailMsg", "邮箱已经存在");
            return map;
        }
        //注册
        user.setSalt(XiaoyuerUtil.generateUUID().substring(0,5));
        user.setPassword(XiaoyuerUtil.md5(user.getPassword()+user.getSalt()));
        user.setType(0);
        user.setActivationCode(XiaoyuerUtil.generateUUID());
        String headerUrl = String.format("http://image.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        user.setHeaderUrl(headerUrl);
        user.setCreateTime(new Date());
        userMapper.insertUser(user);
        //激活
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        String url = domian + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        System.out.println(url);
        String content = templateEngine.process("/mail/activation",context);
        System.out.println(user.getEmail()+"：：：：：："+content);
        mailClient.sendMail(user.getEmail(),"账号激活",content);
        return map;
    }

    public int activation(int userId, String code) {
        User user = userMapper.findUserById(userId);
        if (user.getStatus() == 1) {
            return ACTIVATION_REPEAT;
        } else if (user.getActivationCode().equals(code)) {
            userMapper.updateStatus(1,userId);
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_FAILURE;
        }


    }
    public Map<String,Object> login(String username,String password,int expiredSeconds){
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("usernameMsg", "账号不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("passwordMsg", "密码不能为空");
            return map;
        }
        User user = userMapper.findUserByUserName(username);
        if (user == null) {
            map.put("usernameMsg", "用户名不存在");
            return map;
        }
        if (user.getStatus()!=1) {
            map.put("usernameMsg", "账号未激活");
            return map;
        }
        password = XiaoyuerUtil.md5(password + user.getSalt());
        if (!user.getPassword().equals(password)) {
            map.put("passwordMsg", "密码错误");
            return map;
        }
        //生成登陆凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(XiaoyuerUtil.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+expiredSeconds * 1000));
        loginTicketMappper.insertLoginTicket(loginTicket);
        map.put("ticket", loginTicket.getTicket());
        return map;
    }
    //退出登陆
    public void logout(String ticket) {
        loginTicketMappper.updateStatus(ticket, 1);

    }

    public LoginTicket findLoginTicket(String ticket) {
        return loginTicketMappper.selectByTicket(ticket);
    }
}
