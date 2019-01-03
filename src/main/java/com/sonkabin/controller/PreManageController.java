package com.sonkabin.controller;

import com.sonkabin.entity.PreManage;
import com.sonkabin.service.PreManageService;
import com.sonkabin.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Controller
public class PreManageController {
    @Autowired
    private PreManageService  preManageService;
    @Autowired
    private ProjectService projectService;
    
    @ModelAttribute
    public void getPreManage(@RequestParam(value="preManageId",required=false)Integer id, Map<String,Object>map){
        if(id!=null){
            PreManage preManage=preManageService.findOne(id);
            preManage.setProject(null);
            preManage.setUpdate(LocalDateTime.now());
            map.put("preManage",preManage);
        }
    }
    @GetMapping("/preManages")
    public String toListPage(Model model){
        List<PreManage> preManages=preManageService.findAll();
        model.addAttribute("preManages",preManages);
        return "PreManage/list";
    }


    @GetMapping("/setRules")
    public String toShowPage(){
        return "PreManage/SetRules";
    }


}
