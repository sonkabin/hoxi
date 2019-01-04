package com.sonkabin.controller;

import com.sonkabin.entity.Project;
import com.sonkabin.entity.User;
import com.sonkabin.service.ProjectService;
import com.sonkabin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;


    @ModelAttribute
    public void getProject(@RequestParam(name = "id",required = false) Integer id, Map<String,Object> map){
        if(id!=null){
            Project project=projectService.findOne(id);
            project.setUpdate(LocalDateTime.now());
            project.setUser(null);
            map.put("project",project);
        }
    }

    @GetMapping("/projects")
    public String toListPage(Model model){
        List<Project> projectList=projectService.findAll();
        model.addAttribute("projectList",projectList);
        return "project/list";
    }



    @GetMapping("/addAProject")
    public String toAddAProjectPage(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("projectList",users);
        return "project/addAProject";
    }

    @PostMapping("/addAProject")//保存User
    public String addProject(Project project){//SpringBoot会自动封装对象
        project.setCreate(LocalDateTime.now());
        System.out.println(project);
        project.setUpdate(LocalDateTime.now());
        projectService.saveProject(project);
        return "redirect:/alreadyDeclare";
    }

    @GetMapping("/alreadyDeclare")
    public String toLookDeclaredProjectList(Model model){
        List<Project> projectList=projectService.findAll();
        model.addAttribute("projectList",projectList);
        return "project/alreadyList";
    }



}
