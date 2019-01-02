package com.sonkabin.controller;

import com.sonkabin.entity.UserRole;
import com.sonkabin.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class SettingController {

    @Autowired
    private UserRoleService roleService;

    @ModelAttribute
    public void modelAttribute(@RequestParam(name = "roleId",required = false)Integer id, Map<String,Object> map){
        if(id != null){
            UserRole role = roleService.findById(id);
            map.put("role",role);
        }
    }

    @GetMapping("/settings")
    public String settings(Model model){
        List<UserRole> roles = roleService.findAll();
        model.addAttribute("roles",roles);
        return "setting";
    }


    @GetMapping("/role")
    public String toAddRolePage(){
        return "role/add";
    }

    @PostMapping("/role")
    public String addRole(UserRole role){
        roleService.saveRole(role);
        return "redirect:/settings";
    }

    @GetMapping("/role/{id}")
    public String toEditRolePage(@PathVariable("id")Integer id,Model model){
        UserRole role = roleService.findById(id);
        model.addAttribute("role",role);
        return "role/add";
    }

    @PutMapping("/role")
    public String updateRole(UserRole role){
        roleService.saveRole(role);
        return "redirect:/settings";
    }
}
