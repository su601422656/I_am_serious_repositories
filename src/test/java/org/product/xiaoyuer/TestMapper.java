package org.product.xiaoyuer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.product.xiaoyuer.dao.LoginTicketMappper;
import org.product.xiaoyuer.entity.LoginTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = XiaoyuerApplication.class)
public class TestMapper {
    @Autowired
    private LoginTicketMappper loginTicketMappper;
    @Test
    public void testInsert() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setExpired(new Date());
        loginTicket.setStatus(0);
        loginTicket.setTicket("21331");
        loginTicket.setUserId(12);
        int i = loginTicketMappper.insertLoginTicket(loginTicket);
        System.out.println(i);
    }

    @Test
    public void testSelect() {
       LoginTicket loginTicket = loginTicketMappper.selectByTicket("21331");
       System.out.println(loginTicket);

    }

    @Test
    public void testUpdate() {
        int i = loginTicketMappper.updateStatus("21331", 1);
        System.out.println(i);
    }
}
