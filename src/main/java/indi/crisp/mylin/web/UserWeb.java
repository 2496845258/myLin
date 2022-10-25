package indi.crisp.mylin.web;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.config.AppAnnotate;
import indi.crisp.mylin.pojo.Employee;
import indi.crisp.mylin.pojo.Perm;
import indi.crisp.mylin.service.EmployeeService;
import indi.crisp.mylin.service.PermService;
import indi.crisp.mylin.service.RoleService;
import indi.crisp.mylin.service.impl.EmployeeServiceImpl;
import indi.crisp.mylin.service.impl.PermServiceImpl;
import indi.crisp.mylin.servlet.AllServlet;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Paginate;
import indi.luzhao.summer.annotate.markclass.Controller;
import indi.luzhao.summer.annotate.markmethod.URLMapping;
import indi.luzhao.summer.controller.Transmit;
import indi.luzhao.summer.util.AnalysisJSON;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        EmployeeService service = new EmployeeServiceImpl();
        Feedback<Employee> login = null;
        try {
            login = service.login(req.getParameter("user"), req.getParameter("pwd"));
            // 打印结果
            if (null != login.getResult()){
                Employee result = login.getResult();
                Cookie cookie = new Cookie("role",String.valueOf(result.getErole()));
                Cookie cookie1 = new Cookie("userID",String.valueOf(result.getEmpno()));
                cookie.setMaxAge(6000);
                cookie1.setMaxAge(6000);
                cookie.setHttpOnly(true);
                cookie1.setHttpOnly(true);
                cookie.setPath(req.getContextPath());
                cookie1.setPath(req.getContextPath());
                resp.addCookie(cookie);
                resp.addCookie(cookie1);
                // 将登录成功业务记录在日志中
                return Transmit.sendRequest("/WEB-INF/html/mylin.html");
            }else {
                return Transmit.sendMsg(login.getExplain());
            }
        } catch (AppAbnormal e) {
            // 打印异常
            return  Transmit.sendMsg(login.getExplain());
        }
    }

    @AppAnnotate("注册业务")
    @URLMapping("/hello/register")
    public String x2(HttpServletRequest req) throws ServletException, IOException {
        // 暂时保留 可以通过邮箱注册
        if (req.getParameter("user").equals("123456")){
            if (req.getParameter("pwd").equals("123456")){
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
        PermService service = new PermServiceImpl();
        if (null != req.getCookies()){
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("role")){
                    // 根据用户传入的角色 获取到对应的权限
                    try {
                        Feedback<Paginate<Perm>> permByRoleID = service.findPermByRoleID(Integer.parseInt(cookie.getValue()));
                        Paginate<Perm> result = permByRoleID.getResult();
                        if (null != result){
                            List<Perm> list = result.getList();
                            if (list.size() > 0){
                                String json = AnalysisJSON.getJSON(list);
                                System.out.println(json);
                                return Transmit.sendMsg(json);
                            }
                        }
                    } catch (AppAbnormal e) {
                        System.err.println("查找角色权限失败");
                    }
                    return Transmit.sendMsg("");
                }
            }
        }
        return Transmit.sendMsg("你好");
    }



}
