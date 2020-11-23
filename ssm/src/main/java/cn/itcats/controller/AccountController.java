package cn.itcats.controller;

import cn.itcats.domain.Account;
import cn.itcats.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/***
 * 控制层
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println("表现层查询所有信息。。。。");
        List<Account> list = accountService.findAll();
        model.addAttribute("list",list);
        return "list";
    }
    @RequestMapping("/save")
    public void save(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
        accountService.saveAccount(account);
        System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return;
    }

    @RequestMapping("/findAll2")
    public @ResponseBody
    List<Account> findAll2(Model model){
        System.out.println("表现层查询所有信息。。。。");
        List<Account> list = accountService.findAll();
        model.addAttribute("list",list);
        return list;
    }
}
