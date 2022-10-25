package indi.crisp.mylin.web;

import indi.luzhao.summer.annotate.markclass.Controller;
import indi.luzhao.summer.annotate.markmethod.URLMapping;
import indi.luzhao.summer.controller.Transmit;

@Controller
public class MsgWeb {

    @URLMapping("/hello/login/msg")
    public String x(){
        System.out.println("开始通讯");
        return Transmit.sendMsg("{\"msg\":\"你好\"}");
    }
}
