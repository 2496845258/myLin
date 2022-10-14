package indi.crisp.mylin.servlet;


import indi.luzhao.summer.annotate.markclass.Controller;
import indi.luzhao.summer.controller.Control;
import indi.luzhao.summer.controller.Transmit;
import indi.luzhao.summer.factory.ExampleFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet("/hello/*")
public class AllServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ExampleFactory.getInstance();
        Transmit.sendMsg("666");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        myinit(req, resp);
    }

    /**
     * 自定义业务处理
     * @param req
     * @param resp
     */
    protected void myinit(HttpServletRequest req, HttpServletResponse resp) {
        var url = req.getRequestURI().substring(req.getContextPath().length());
        Control control = (Control) ExampleFactory.getInstance().getAnnMap(Controller.class).get(url);
        if ( null == control ) {
            //404
            try {
                resp.sendError(404, url + "找不到");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        //找到了，开始进行业务处理
        //1. 参数
        var list = analysisParameterAuto(control, req, resp);

        //2. 执行方法（需要参数数组）
        var obj = skipMethod(control, list.toArray());
        //3. 解析返回结果
        returnMethod(control, obj, req, resp);

    }

    /**
     * 执行方法
     * @param control
     * @param objects
     * @return
     */
    private Object skipMethod(Control control, Object[] objects) {
        try {
            return control.getMethod().invoke(control.getObject(), objects);
        } catch (InvocationTargetException e) {
            throw new RuntimeException( control.getMethod().getName() +  ":目标方法执行过程中产生异常");
        } catch (IllegalAccessException e) {
            throw new RuntimeException( control.getMethod().getName() +  ":目标方法执行错误，原因权限不足");
        }
    }


    private List<Object> analysisParameterAuto(Control control, HttpServletRequest req, HttpServletResponse resp) {
        var list = new LinkedList<Object>();
        for ( var i : control.getParms() ) {
            //01:444
            var t1 = i.substring(0, 2);
            if ( t1.equals("01") ) {
                var t2 = i.substring(3);
                list.add(req.getParameter(t2));
            } else if ( t1.equals("02") ) {
                //走实体类
                try {
                    var t2 = i.substring(3);
                    list.add(setParamValue(Class.forName(t2), req));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("实体类注入失败");
                }
            } else if ( t1.equals("03") ) {
                var index1 = i.indexOf(':', 3);
                var t2 = i.substring(3, index1);
                var t3 = i.substring(index1+1);

                Object obj = null;
                switch ( t3 ) {
                    case "java.lang.String": obj = req.getParameter(t2); break;
                    case "int" :
                    case "java.lang.Integer": obj = Integer.valueOf(req.getParameter(t2)); break;
                    case "char" :
                    case "java.lang.Character": obj = Character.valueOf(req.getParameter(t2).charAt(0)); break;
                    case "long" :
                    case "java.lang.Long": obj = Long.valueOf(req.getParameter(t2));break;
                    case "double" :
                    case "java.lang.Double": obj = Double.valueOf(req.getParameter(t2)); break;
                    case "float" :
                    case "java.lang.Float": obj = Float.valueOf(req.getParameter(t2)); break;
                    case "javax.servlet.http.HttpServletRequest": obj = req; break;
                    case "javax.servlet.http.HttpServletResponse": obj = resp; break;
                    case "javax.servlet.http.HttpSession": obj = req.getSession(); break;
                    case "javax.servlet.http.Part": {
                        try {
                            obj = req.getPart(t2);
                        } catch (Exception e) {
                            System.err.println("获取part失败");
                            obj = null;
                        }
                    } break;
                    default: {
                        //未知类型注入null
                        obj = null;
                    }
                }
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 装配基本数据类型
     * @param clazz
     * @param value
     * @return
     */
    private Object setParamValue(Class clazz, String value) {
        if ( null == value ) {
            return null;
        }
        Object obj = null;
        if ( clazz == String.class ) {
            obj = value;
        } else if ( clazz == int.class || clazz == Integer.class ) {
            obj = Integer.valueOf(value);
        } else if ( clazz == long.class || clazz == Long.class ) {
            obj = Long.valueOf(value);
        } else if ( clazz == char.class || clazz == Character.class ) {
            obj = Character.valueOf(value.charAt(0));
        } else if ( clazz == double.class || clazz == Double.class ) {
            obj = Double.valueOf(value);
        } else if ( clazz == float.class || clazz == Float.class ) {
            obj = Float.valueOf(value);
        }
        return obj;
    }
    /**
     * 装配进入实体类
     * @param clazz
     * @param req
     * @return
     */
    private Object setParamValue(Class clazz, HttpServletRequest req) {
        Object obj = null;
        try {
             obj = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(clazz.getName() + "无法成功构造");
        }
        if ( null == obj ) {
            return null;
        }
        for ( var i : clazz.getDeclaredFields() ) {
            try {
                clazz.getMethod("set" + smallBig(i.getName()), i.getType())
                        .invoke(obj, setParamValue(i.getType(), req.getParameter(i.getName())));
            } catch (Exception e) {
                System.err.println(clazz.getName() + "set方法注入失败");
            }
        }
        return obj;
    }

    /**
     * 小写转大写
     * @param str
     * @return
     */
    private String smallBig(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }




    /**
     * 处理方法返回
     * @param control
     * @param object
     * @param req
     * @param resp
     */

    private void returnMethod(Control control, Object object, HttpServletRequest req, HttpServletResponse resp) {


        //默认不做出响应
        if (null == object){
            //响应404
            try {
                resp.sendError(404, req.getContextPath() + "找不到");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 方法返回 类型是Sting 则为
        if (object instanceof String){
            analysisProtocol(object, req ,resp);
        }else {
            if (object instanceof Map){

            }
        }
    }


    /**
     * 处理消息
     * @param mReturn
     */
    private void analysisProtocol(Object mReturn, HttpServletRequest req, HttpServletResponse resp){
        String msg = "";
        String msgType = "";
        String url = "";
        String classpath = "";
        // 寻找第一个分隔符
        int first_condition = mReturn.toString().indexOf("|");
        int second_condition;
        int third_condition;
        // 请求消息返回类型
        msgType = mReturn.toString().substring(0,first_condition);
        // 格式为 消息类型 + URL/消息
        if (first_condition == mReturn.toString().lastIndexOf("|")){
            msg = mReturn.toString().substring(first_condition+1);
            url = msg;
            classpath = null;
        }else {
            second_condition = mReturn.toString().indexOf("|",first_condition+1);
            // 格式为 消息类型 + URL + 消息
            url = mReturn.toString().substring(first_condition+1,second_condition);
            msg = mReturn.toString().substring(second_condition+1);
            classpath = null;
            // 特殊情况 有类名
            if (second_condition != mReturn.toString().lastIndexOf("|")){
                third_condition = mReturn.toString().indexOf("|",second_condition+1);
                msg = mReturn.toString().substring(third_condition+1);
                classpath = mReturn.toString().substring(second_condition+1,third_condition);
            }
        }

        try {
            executionRequest(req, resp, msgType, url, msg, classpath);
        } catch (Exception e) {
            throw new RuntimeException("跳转失败");
        }
    }

    private void executionRequest(HttpServletRequest req, HttpServletResponse resp, String msgType, String url, String msg, String classpath) throws ServletException, IOException {

        switch (msgType){
            // 返回格式为Json格式
            case "Json" :
            case "Text" : {
                PrintWriter writer = resp.getWriter();
                writer.println(msg);
                writer.flush();
                writer.close();
                break;
            }
            // 请求重定向
            case "Send_Redirect" : {
                resp.sendRedirect(url);
                return;
            }
            // 仅请求转发
            case "Request_Forward" : {
                req.getRequestDispatcher(url).forward(req,resp);
                break;
            }
            // 带Json的请求转发
            case "Request_Forward_JSON" :
                // 带消息的请求转发
            case "Request_Forward_Text" : {
                req.setAttribute("msg",msg);
                req.getRequestDispatcher(url).forward(req,resp);
                break;
            }
//            case "UpLoad" : {
//                int index = msg.indexOf(":");
//                String spart = msg.substring(0,index);
//                String sname = msg.substring(index+1);
//                Part file = req.getPart(spart);
//                String path = "E:\\Test";
//                // 文件 可以通过路径直接将自己写入
//                file.write(path+"\\"+url+".jpg");
//                break;
//            }
//            case "DownLoad" : {
//                // 读入数据
//                RandomAccessFile rand = new RandomAccessFile("E:\\Test\\test.jpg","rwd");
//                byte[] b = new byte[(int)rand.length()];
//                rand.read(b);
//                resp.addHeader("Content-Disposition","attachment;fileName="+msg);
//                resp.getOutputStream().write(b);
//                break;
//            }
            case "Request_Forward_Session" : {
                int index = msg.indexOf(":");
                String key = msg.substring(0,index);
                String value = msg.substring(index+1);
                req.getSession().setAttribute(key,value);
                if (!url.equals(msg)){
                    req.getRequestDispatcher(url).forward(req,resp);
                }
                break;
            }
            case "Request_Forward_Application" : {
                int index = msg.indexOf(":");
                String key = msg.substring(0,index);
                String value = msg.substring(index);
                req.getSession().getServletContext().setAttribute(key,value);
                if (!url.equals(msg)){
                    req.getRequestDispatcher(url).forward(req,resp);
                }
                break;

            }
            case "Request_Forward_Cookie" : {
                int index = msg.indexOf(":");
                String key = msg.substring(0,index);
                String value = msg.substring(index);
                Cookie cookie = new Cookie(key,value);
                //设置过期时间
                cookie.setMaxAge(20);
                cookie.setPath(req.getContextPath());
                cookie.setHttpOnly(true);
                resp.addCookie(cookie);
                if (!url.equals(msg)){
                    resp.sendRedirect(url);
                }
                break;

            }
            // 带List的请求转发
            case "Request_Forward_JsonList" : {
                try {
                    Class<?> clazz = Class.forName(classpath);
                    req.setAttribute("msg", Transmit.myExampleList(msg,clazz));
                    req.getRequestDispatcher(url).forward(req,resp);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException("获取Class对象失败");
                }
                break;
            }
        }
        resp.setStatus(200);
    }

    public static String upLoad(String path, String filepath,HttpServletRequest req) throws ServletException, IOException {
        return upLoad(path,filepath,null,req);
    }
    public static String upLoad(String path, String filep, String filename,HttpServletRequest req) throws ServletException, IOException {
        Part file = req.getPart(filep);
        String filetype = file.getSubmittedFileName();
        if (filetype.lastIndexOf(".") != -1){
            filetype = filetype.substring(filetype.lastIndexOf("."));
        }else {
            filetype = "";
        }
        String str = null;
        // 文件 可以通过路径直接将自己写入
        if (null == filename){
            str = Transmit.systemPath();
            file.write(path+"\\"+str+filetype);
            return str+filetype;
        }else {
            str = req.getParameter(filename);
            file.write(path+"\\"+str+filetype);
            return str+filetype;
        }
    }

    public static void downLoad(String path, String filename, HttpServletResponse resp) throws IOException {

        RandomAccessFile rand = new RandomAccessFile(path+"\\"+filename,"rwd");
        byte[] b = new byte[(int)rand.length()];
        rand.read(b);
        resp.addHeader("Content-Disposition","attachment;fileName="+filename);
        resp.getOutputStream().write(b);
        resp.getOutputStream().flush();
        resp.getOutputStream().close();
        resp.setStatus(200);
    }


}
