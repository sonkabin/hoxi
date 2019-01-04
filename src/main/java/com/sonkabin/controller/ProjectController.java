package com.sonkabin.controller;

import com.sonkabin.entity.Project;
import com.sonkabin.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;


    @ModelAttribute
    public void getProject(@RequestParam(name = "id",required = false) Integer id, Map<String,Object> map){
        if(id!=null){
            Project project=projectService.findOne(id);
            project.setUpdate(LocalDateTime.now());
            map.put("project",project);
        }
    }

    @GetMapping("/projects")
    public String toListPage(Model model){
        List<Project> projectList=projectService.findAll();
        model.addAttribute("projectList",projectList);
        return "project/list";
    }



}
