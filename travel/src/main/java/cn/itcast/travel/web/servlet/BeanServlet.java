package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BeanServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * servlet分发
         */
        String URI = req.getRequestURI();
        String methedName = URI.substring(URI.lastIndexOf("/") + 1);

        try {
            Method method = this.getClass().getMethod(methedName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将传入数据序列化为json，发送回客户端
     * @param obj
     * @param response
     * @throws IOException
     */
    public void writerValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     * 将传入数据序列化为json
     * @param obj
     * @param response
     * @return
     * @throws IOException
     */
    public String writerAsValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(obj);
        return json;
    }
}
