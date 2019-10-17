package org.product.xiaoyuer;

import org.junit.runner.RunWith;
import org.product.xiaoyuer.util.XiaoyuerConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Test implements XiaoyuerConstant {
    private  static final Logger logger = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void testDome() {
        int anInt = 1 == 3 ? DEFAULT_EXPIRED_SECONDS : REMEMBER_EXPIRED_SECONDS;
        System.out.println(anInt);
    }

    @org.junit.Test
    public void testLog() {
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }

}
