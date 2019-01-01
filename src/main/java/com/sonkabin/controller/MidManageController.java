package com.sonkabin.controller;

import com.sonkabin.entity.MidManage;
import com.sonkabin.service.MidManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MidManageController{

    @Autowired
    private MidManageService midManageService;

    @GetMapping("/midManage")
    public String tolistPage(Model model){
        List<MidManage> midManage= midManageService.findAll();
        model.addAttribute("midManage",midManage);
        return "midManage/list";
    }

}
