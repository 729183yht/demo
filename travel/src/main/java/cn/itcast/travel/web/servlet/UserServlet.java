package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserServlce;
import cn.itcast.travel.service.impl.UserServlceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BeanServlet {
    private UserServlce servlce=new UserServlceImpl();
    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        //json格式化
        ObjectMapper mapper=new ObjectMapper();
        //保存错误信息
        ResultInfo info=new ResultInfo();

        //验证验证码
        String check = request.getParameter("check");
        HttpSession check_session=request.getSession();
        String checkcode_server = (String) check_session.getAttribute("CHECKCODE_SERVER");
        //防止重复使用验证码
        check_session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode_server==null || !check.equalsIgnoreCase(checkcode_server)){
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            /*String json = mapper.writeValueAsString(info);
            response.getWriter().write(json);*/
            writerValue(info,response);
            return;
        }
        User user=new User();
        //接收前端数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            //存到bean对象中
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用注册service

        boolean falg=servlce.regist(user);

        //相应结果
        if(falg){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        /*String json = mapper.writeValueAsString(info);
        response.getWriter().write(json);*/
        writerValue(info,response);
    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        //判断验证码
        String check = request.getParameter("check");
        HttpSession session=request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //返回前端状态信息
        ResultInfo resultInfo=new ResultInfo();
        ObjectMapper mapper=new ObjectMapper();
        if(!checkcode_server.equalsIgnoreCase(check)){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误，请重新输入");

            /*String json = mapper.writeValueAsString(resultInfo);
            response.getWriter().write(json);*/
            writerValue(resultInfo,response);
            return;
        }
        //接收前端数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用业务接口

        User u = servlce.login(user);
        //判断用户名密码是否正确
        if(u==null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("账户或密码错误");
        }
        //判断用户是否激活
        if(u!=null && !"Y".equals(u.getStatus())){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户未激活");
        }
        //用户登录成功
        if(u!=null && "Y".equals(u.getStatus())){
            session.removeAttribute("CHECKCODE_SERVER");
            request.getSession().setAttribute("user",u);
            resultInfo.setFlag(true);
        }
        /*String json = mapper.writeValueAsString(resultInfo);
        response.getWriter().write(json);*/
        writerValue(resultInfo,response);
    }

    /**
     * session查找用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        Object user = request.getSession().getAttribute("user");
        ObjectMapper mapper=new ObjectMapper();
        /*String json = mapper.writeValueAsString(user);
        response.getWriter().write(json);*/
        writerValue(user,response);
    }

    /**
     * 退出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/index.html");
    }

    /**
     * 激活
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if(code!=null){
            boolean b = servlce.activeUser(code);
            String msg=null;
            if(b){

                msg="激活成功<a href='"+request.getContextPath()+"/login.html'>登录</a>";

            }else {
                //激活失败
                msg="激活失败，联系管理员";
            }
            response.getWriter().write(msg);
        }
    }

}
