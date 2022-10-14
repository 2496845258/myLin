package indi.crisp.mylin.web;

import indi.crisp.mylin.config.AppAnnotate;
import indi.crisp.mylin.servlet.AllServlet;
import indi.luzhao.summer.annotate.markclass.Controller;
import indi.luzhao.summer.annotate.markmethod.URLMapping;
import indi.luzhao.summer.controller.Transmit;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserWeb {

    @AppAnnotate("跳转登录")
    @URLMapping("/hello/skiplogin")
    public String login(){

        return Transmit.sendRequest("/WEB-INF/html/login.html");
    }

    @AppAnnotate("跳转注册")
    @URLMapping("/hello/skipregister")
    public String x(){

        return Transmit.sendRequest("/WEB-INF/html/register.html");
    }

    @AppAnnotate("登录业务")
    @URLMapping("/hello/login")
    public String x1(HttpServletRequest req, HttpServletResponse resp){
        if (req.getParameter("user").equals("123456")){
            if (req.getParameter("pwd").equals("123456")){
                Cookie cookie = new Cookie("role", req.getParameter("role"));
                cookie.setMaxAge(60);
                cookie.setHttpOnly(true);
                cookie.setPath(req.getContextPath());
                resp.addCookie(cookie);
                return Transmit.sendRequest("/WEB-INF/html/mylin.html");
            }else {
                return Transmit.sendMsg("密码错误");
            }
        }else {
            return  Transmit.sendMsg("登录失败");
        }
    }

    @AppAnnotate("注册业务")
    @URLMapping("/hello/register")
    public String x2(HttpServletRequest req) throws ServletException, IOException {
        if (req.getParameter("user").equals("123456")){
            if (req.getParameter("pwd").equals("123456")){
                AllServlet.upLoad("E:\\Test","file","desc",req);
                return Transmit.sendRequest("/WEB-INF/html/mylin.html");
            }else {
                return Transmit.sendMsg("密码不一致错误");
            }
        }else {
            return  Transmit.sendMsg("注册失败");
        }
    }

    @AppAnnotate("判断当前用户角色")
    @URLMapping("/hello/business")
    public String x3(HttpServletRequest req, HttpServletResponse resp){
        if (null != req.getCookies()){
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("role")){
                    return Transmit.sendMsg("{\"roles\":"+cookie.getValue()+",\"perm\":[\"便签\",\"请假\",\"通知\",\"审核\"]}");
                }
            }
        }
        return Transmit.sendMsg("你好");
    }



}
