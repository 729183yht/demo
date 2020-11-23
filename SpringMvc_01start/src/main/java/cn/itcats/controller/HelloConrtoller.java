package cn.itcats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*控制器类*/
@Controller
@RequestMapping(path = "/user")
public class HelloConrtoller {

    @RequestMapping(path = "/hello")
    public String sayHello(){
        System.out.println("hello stringmvc");
        //jsp的名字

        return "success";
    }
    @RequestMapping(path = "/testRequestMapping")
    public String testRequestMapping(){
        System.out.println("requestMapping注解测试");
        return "success";
    }
}
