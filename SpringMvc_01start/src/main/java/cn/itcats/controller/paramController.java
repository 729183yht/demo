package cn.itcats.controller;

import cn.itcats.domain.Account;
import cn.itcats.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;
import java.util.Map;

/**
 * 请求参数绑定
 */
@Controller
@RequestMapping("/param")
@SessionAttributes(value = {"msg"})
public class paramController {

    @RequestMapping("testParam")
    public String testParam(String username){
        System.out.println("用户名"+username);
        return "success";
    }

    /*
    * 请求参数绑定
    * */

    @RequestMapping(path = "saveAccount",method ={RequestMethod.POST})
    public String saveAccount(Account account){
        System.out.println(account);
        return "success";
    }

    @RequestMapping(" ")
    public String saveUser(User user){
        System.out.println(user);
        return "success";
    }

    @RequestMapping("testModelAttribute")
    public String testModelAttribute(@ModelAttribute("abc") User user){
        System.out.println(user);
        return "success";
    }

    @ModelAttribute
    public void showUser(String uname, Map<String,User> map){
        User user=new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        map.put("abc",user);
    }

    /*@ModelAttribute
    public User showUser(String uname){
        User user=new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        return user;
    }*/


    @RequestMapping("testSession")
    public String testSession(Model model){
        model.addAttribute("msg","美美");

        return "success";
    }

    /**
     *从session中取值
     * @param modelMap
     * @return
     */
    @RequestMapping("testgetSession")
    public String testgetSession(ModelMap modelMap){
        String msg = (String) modelMap.get("msg");
        System.out.println(msg);

        return "success";
    }
}
