package com.sonkabin.controller;

import com.sonkabin.entity.Department;
import com.sonkabin.entity.User;
import com.sonkabin.entity.UserRole;
import com.sonkabin.service.DepartmentService;
import com.sonkabin.service.UserRoleService;
import com.sonkabin.service.UserService;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserRoleService roleService;


    @ModelAttribute
    public void getUser(@RequestParam(value = "id",required = false)Integer id, Map<String,Object> map){
        if(id != null){//被ModelAttribute标注的方法每次请求发送过来都会被调用
            User user = userService.findOne(id);
            user.setDept(null);
            user.setRole(null);
            user.setUpdate(LocalDateTime.now());
            map.put("user",user);
        }
    }

    @GetMapping("/users")
    public String toListPage(Model model){//请求所有用户
        List<User> users = userService.findAll();
        model.addAttribute("users",users);//放到模型，提供thymeleaf
        return "user/list";//thymeleaf会将此字符串解析，在前面添加templates，后面添加.html
    }

    @GetMapping("/user")//请求添加页面
    public String toAddPage(Model model){
        List<Department> depts = departmentService.findAll();
        List<UserRole> roles = roleService.findAll();
        model.addAttribute("depts",depts);
        model.addAttribute("roles",roles);
        return "user/add";
    }

    @PostMapping("/user")//保存User
    public String addUser(User user){//SpringBoot会自动封装对象
        user.setCreate(LocalDateTime.now());
        System.out.println(user);
        user.setUpdate(LocalDateTime.now());

        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user/{id}")
    public String toEditPage(@PathVariable("id")Integer id,Model model){//@PathVariable能将请求中的id取出
        User user = userService.findOne(id);
        List<Department> depts = departmentService.findAll();
        List<UserRole> roles = roleService.findAll();
        model.addAttribute("user",user);
        model.addAttribute("depts",depts);
        model.addAttribute("roles",roles);
        return "/user/add";
    }

    @PutMapping("/user")
    public String updateUser(User user){//更新用户

        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id")Integer id){//通过id删除User
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PutMapping("/reset/{id}")
    public String resetPwd(@PathVariable("id")Integer id){

        return "redirect:/users";
    }

    @GetMapping("/404")
    public String to404(){
        return "404";
    }

}
