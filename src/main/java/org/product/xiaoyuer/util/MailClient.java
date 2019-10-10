package org.product.xiaoyuer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.standard.expression.MessageExpression;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailClient {
    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to, String subject, String context) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            System.out.println("from邮箱：：：：："+from);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(context,true);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            System.out.println("失败");
            e.printStackTrace();
        }
    }

}
