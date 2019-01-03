package com.sonkabin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PreManageController {


    @GetMapping("/showRules")
    public String toShowPage(){
        return "PreManage/DeclareRuleandEntrance";
    }

}
