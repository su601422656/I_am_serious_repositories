package org.product.xiaoyuer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.product.xiaoyuer.util.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = XiaoyuerApplication.class)
public class MailTest {
    @Autowired
    private MailClient mailClient;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void mailTest() {
        mailClient.sendMail("601422656@qq.com","你老公发的","我写注册功能发邮件的东西，就试试而已");
    }

    @Test
    public void testHTMLMail() {
        Context context = new Context();
        context.setVariable("username","王瑶");
        String contextHtml = templateEngine.process("/mail/activation", context);
        System.out.println(contextHtml);
        mailClient.sendMail("601422656@qq.com","HTML","dadasdada");
    }
}


