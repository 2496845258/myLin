package indi.crisp.mylin.web;

import indi.crisp.mylin.abnormal.AppAbnormal;
import indi.crisp.mylin.config.AppAnnotate;
import indi.crisp.mylin.pojo.Employee;
import indi.crisp.mylin.pojo.Leave;
import indi.crisp.mylin.pojo.expand.LeavePO;
import indi.crisp.mylin.pojo.expand.LeaveVO;
import indi.crisp.mylin.service.EmployeeService;
import indi.crisp.mylin.service.LeaveService;
import indi.crisp.mylin.service.impl.EmployeeServiceImpl;
import indi.crisp.mylin.service.impl.LeaveServiceImpl;
import indi.crisp.mylin.util.Feedback;
import indi.crisp.mylin.util.Paginate;
import indi.luzhao.summer.annotate.markclass.Controller;
import indi.luzhao.summer.annotate.markmethod.URLMapping;
import indi.luzhao.summer.controller.Transmit;
import indi.luzhao.summer.util.AnalysisJSON;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
public class LeaveWeb {

    @AppAnnotate("请假记录")
    @URLMapping("/hello/login/leave")
    public String qj(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        LeaveService leaveService = new LeaveServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("userID")){
                System.out.println("用户ID:"+cookie.getValue());
                Feedback<Paginate<Leave>> empList = null;
                try {
                    empList = leaveService.findEmpList(Integer.parseInt(cookie.getValue()), 0, 9);
                } catch (AppAbnormal e) {
                    System.err.println("查询请假记录异常");
                }

                Paginate<Leave> result = empList.getResult();
                if (result != null){
                    List<Leave> list = result.getList();
                    if (list.size() > 0){
                        List<LeaveVO> leaveVOS = new LinkedList<>();
                        Object obj = list;


                        String jsons = AnalysisJSON.getJSON(leaveVOS);
                        System.out.println(jsons);
                        return Transmit.sendMsg(jsons);
                    }
                }

            }
        }
        return Transmit.sendMsg("");
//        return Transmit.sendMsg("[{\"qjr\":\"张三\",\"kssj\":\"2022-06-21\",\"qjyy\":\"婚假\",\"jsjj\":\"2022-06-25\"}," +
//                "{\"qjr\":\"李四\",\"kssj\":\"2022-06-21\",\"qjyy\":\"事假\",\"jsjj\":\"2022-06-25\"},{\"name\":\"张三\"}]");
    }
}
