package com.sonkabin.controller;

import com.sonkabin.entity.PostManage;
import com.sonkabin.service.PostManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PostManageController {

    @Autowired
    private PostManageService postManageService;

    @GetMapping("/postManages")
    public String tolistPage(Model model){
        List<PostManage> postManages= postManageService.findAll();
        model.addAttribute("postManage",postManages);
        return "postManage/list";
    }
}
