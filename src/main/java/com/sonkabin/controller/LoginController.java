package com.sonkabin.controller;

import com.sonkabin.entity.User;
import com.sonkabin.service.MessageService;
import com.sonkabin.service.UserService;
import com.sonkabin.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @PostMapping("/user/login")
    public String login(@RequestParam("username")String username, @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        User user = userService.login(username,password);
        //1表示教职工，2表示学校职能部门工作人员，3表示学院行政管理人员，4表示外部评审专家
        if(!ObjectUtils.isEmpty(user)){
            session.setAttribute("loginUser",username);
            if("教职工".equals(user.getRole().getName())){
                session.setAttribute("id",user.getId());
                session.setAttribute("mark",1);
                long count = messageService.count(user.getId());
                session.setAttribute("count",count);
            }else if("学校职能部门工作人员".equals(user.getRole().getName())){
                session.setAttribute("mark",2);
            }else if("学院行政管理人员".equals(user.getRole().getName())){
                session.setAttribute("mark",3);
            }else{
                session.setAttribute("mark",4);
            }
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名或密码错误");
            return "login";
        }
    }
}
