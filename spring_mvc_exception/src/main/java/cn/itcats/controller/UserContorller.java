package cn.itcats.controller;


import cn.itcats.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/*
* 1.编写自定义异常类（做提示信息）
* 2.编写异常处理器
* 3.配置异常处理器（跳转到提示页面）
*
* */
@Controller
@RequestMapping("/user")
public class UserContorller  {
    @RequestMapping("/testException")
    public String testException() throws SysException {
        System.out.println("异常处理");
        try {
            int i=10/0;
        } catch (Exception e) {
            e.printStackTrace();
            //t
            throw new SysException("查询所有用户出现错误");
        }
        return "success";
    }
}
