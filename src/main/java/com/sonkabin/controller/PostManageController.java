package com.sonkabin.controller;

import com.sonkabin.entity.PostManage;
import com.sonkabin.entity.Project;
import com.sonkabin.service.PostManageService;
import com.sonkabin.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            PostManage postManage = postManageService.findOne(id);
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

    @GetMapping("/postManage/{id}")
    public String toEditPage(@PathVariable("id")Integer id, Model model){//@PathVariable能将请求中的id取出
        PostManage postManage = postManageService.findOne(id);
        List<Project> projects = projectService.findAll();
        model.addAttribute("postManage",postManage);
        model.addAttribute("projects",projects);
        return "/postManage/edit";
    }

    @PutMapping("/postManage" )
    public String updatePostManage(PostManage postManage){//更新结题日期
        postManageService.savePostManage(postManage);
        return "redirect:/postManages";
    }

    @GetMapping("/reject/{id}")
    public String toRejectPage(@PathVariable("id")Integer id, Model model){//@PathVariable能将请求中的id取出
        PostManage postManage = postManageService.findOne(id);
        List<Project> projects = projectService.findAll();
        model.addAttribute("postManage",postManage);
        model.addAttribute("projects",projects);
        return "/postManage/reject";
    }

    @PutMapping("/updatereject")
    public String updateReject(PostManage postManage){//更新结题日期
        postManageService.savePostManage(postManage);
        return "redirect:/postManages";
    }

    @PutMapping("/submit")
    public String submit(PostManage postManage){//更新结题日期
        postManageService.savePostManage(postManage);
        return "redirect:/postManages";
    }

//    @InitBinder
//    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormat.setLenient(false);
//        binder.registerCustomEditor(LocalDateTime.class, new CustomDateEditor(dateFormat, false));
//    }
}
