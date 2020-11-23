package cn.itcats.controller;

import cn.itcats.domin.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 返回值为String
     * @param model
     * @return
     */
    @RequestMapping("/testString")
    public String testString(Model model){

        User user=new User();
        user.setUsername("妹妹");
        user.setPassword("123");
        user.setAge(23);
        model.addAttribute(user);
        return "success";
    }



    @RequestMapping("/testVoid")
    public void testVoid(){

        System.out.println("方法执行了");


    }

    /**
     *异步请求
     */

    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println(user);
        user.setUsername("哈哈");
        user.setAge(40);
        return user;
    }
}
