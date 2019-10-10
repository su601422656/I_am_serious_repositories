package org.product.xiaoyuer.controller;

import org.product.xiaoyuer.util.XiaoyuerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(value = "/set_cookie")
    @ResponseBody
    public String setCoookie(HttpServletResponse httpServletResponse) {
        Cookie c = new Cookie("code", XiaoyuerUtil.generateUUID());
        c.setPath("/test");
        c.setMaxAge(60 * 60 * 4);
        httpServletResponse.addCookie(c);

        return "set_cookie";

    }

    @RequestMapping(value = "/get_cookie")
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {

        System.out.println(code);


        return "get cookie";

    }

    @RequestMapping(value = "/set_session")
    @ResponseBody
    public String setSession(HttpSession httpSession) {
        httpSession.setAttribute("id",1);
        httpSession.setAttribute("name","德华");
        return "set session";

    }

    @RequestMapping(value = "/get_session")
    @ResponseBody
    public String getSession(HttpSession httpSession) {
        System.out.println(httpSession.getAttribute("id")+":"+httpSession.getAttribute("name"));

        return "get_session";

    }
}
