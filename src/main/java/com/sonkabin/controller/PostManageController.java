package com.sonkabin.controller;

import com.sonkabin.entity.PostManage;
import com.sonkabin.entity.Project;
import com.sonkabin.service.PostManageService;
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
public class PostManageController {

    @Autowired
    private PostManageService postManageService;
    @Autowired
    private ProjectService projectService;

    @ModelAttribute
    public void getPostManage(@RequestParam(value = "postManageId",required = false)Integer id, Map<String,Object> map){
            if(id != null){//被ModelAttribute标注的方法每次请求发送过来都会被调用
            PostManage postManage = postManageService.findById(id);
            postManage.setProject(null);
            postManage.setUpdate(LocalDateTime.now());
            map.put("postManage",postManage);
        }
    }

    @GetMapping("/postManages")
    public String toListPage(Model model){
        List<PostManage> postManages= postManageService.findAll();
        model.addAttribute("postManages",postManages);
        return "postManage/list";
    }


    @GetMapping("/postManage")//请求添加页面
    public String toAddPage(Model model){
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects",projects);
        return "postManage/edit";
    }
}
