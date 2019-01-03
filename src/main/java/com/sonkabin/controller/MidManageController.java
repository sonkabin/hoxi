package com.sonkabin.controller;

import com.sonkabin.entity.MidManage;
import com.sonkabin.entity.Project;
import com.sonkabin.service.MidManageService;
import com.sonkabin.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class MidManageController{

    @Autowired
    private MidManageService midManageService;
    @Autowired
    private ProjectService projectService;

    @ModelAttribute
    public void getMidManage(@RequestParam(value = "midManageId",required = false)Integer id, Map<String,Object>map){
        if(id!=null){
            MidManage midManage = midManageService.findOne(id);
            midManage.setProject(null);
            midManage.setUpdate(LocalDateTime.now());
            map.put("midManage",midManage);
        }
    }

    @GetMapping("/midManages")
    public String toListPage(Model model){
        List<MidManage> midManages= midManageService.findAll();
        model.addAttribute("midManages",midManages);
        return "midManage/list";
    }

    @GetMapping("/tecmidManages")
    public String toTeacherListPage(Model model){
        List<MidManage> midManages= midManageService.findAll();
        model.addAttribute("midManages",midManages);
        return "midManage/teacherlist";
    }

    @GetMapping("/midManage/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        MidManage midManage = midManageService.findOne(id);
        List<Project> projects = projectService.findAll();
        model.addAttribute("midManage",midManage);
        model.addAttribute("projects",projects);
        return "/midManage/edit";

    }
    @PutMapping("/midManage")
    public String updateMidManage(MidManage midManage){
        midManageService.saveMidManage(midManage);
        return "redirect:/midManages";
    }

    @GetMapping("/check/{id}")
    public String toCheckPage(@PathVariable("id") Integer id,Model model){
        MidManage midManage = midManageService.findOne(id);
        List<Project> projects = projectService.findAll();
        model.addAttribute("midManage",midManage);
        model.addAttribute("projects",projects);
        return "/midManage/check";
    }

    @PutMapping("/check")
    public String updateCheck(MidManage midManage){
        midManageService.saveMidManage(midManage);
        return "redirect:/midManages";
    }

    @GetMapping("/midsubmit/{id}")
    public String toMidSubmitPage(@PathVariable("id") Integer id, Model model){
        MidManage midManage = midManageService.findOne(id);
        List<Project> projects = projectService.findAll();
        model.addAttribute("midManage",midManage);
        model.addAttribute("projects",projects);
        return "/midManage/submit";
    }

    @PutMapping("/midsubmit")
    public String updateMidSubmit(MidManage midManage){
        midManageService.saveMidManage(midManage);
        return "redirect:/tecmidManages";
    }


}
