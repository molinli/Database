package com.system.controller;

import com.system.po.StatusSingleTon;
import com.system.po.Userlogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    //表示当前状态量
    private StatusSingleTon statusSingleTon=StatusSingleTon.getStatusSingleTon();

    //登录跳转
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String loginUI() throws Exception {
        return "../../login";
    }

    //登录表单处理
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(Userlogin userlogin) throws Exception {

        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getUsername(),
                userlogin.getPassword());
        Subject subject = SecurityUtils.getSubject();

        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        subject.login(token);

        if (subject.hasRole("admin")) {

            return "redirect:/admin/showStudent";
        } else if (subject.hasRole("teacher")) {
            return "redirect:/teacher/showTopic";
        } else if (subject.hasRole("student")) {
            return "redirect:/student/showTopic";
        }

        //重新到login
        return "/login";
    }
}
