package com.sonkabin.controller;

import com.sonkabin.entity.PreManage;
import com.sonkabin.entity.Project;
import com.sonkabin.entity.User;
import com.sonkabin.service.ProjectService;
import com.sonkabin.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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
    public String toListPage(Model model){//请求所有用户
        List<Project> projectList=projectService.findAll();
        model.addAttribute("projectList",projectList);//放到模型
        return "project/list";//thymeleaf会将此字符串解析，在前面添加templates，后面添加.html
    }



    @GetMapping("/addAProject")//请求添加页面
    public String toAddAProjectPage(Model model){

        List<User> users = userService.findAll();
        model.addAttribute("projectList",users);
        return "project/addAProject";
    }

    @PostMapping("/addAProject")//保存Project
    public String addProject(Project project,@RequestParam("file") MultipartFile file)throws IOException {//SpringBoot会自动封装对象
        project.setCreate(LocalDateTime.now());
        System.out.println(project);
        project.setUpdate(LocalDateTime.now());
        projectService.saveProject(project,file);
        return "redirect:/alreadyDeclare";//返回不确定？？模糊projects
    }

    @GetMapping("/alreadyDeclare")
    public String toLookDeclaredProjectList(Model model){
        List<Project> projectList=projectService.findAll();
        model.addAttribute("projectList",projectList);
        return "project/alreadyList";
    }

    @GetMapping("/project/{id}")
    public String toEditPage(@PathVariable("id")Integer id,Model model){
        Project project=projectService.findOne(id);
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        model.addAttribute("project",project);
        return "/project/edit";
    }

    @PutMapping("/project")
    public String updateproject(Project project,HttpSession session){
        Integer id = (Integer)session.getAttribute("id");
        projectService.saveProject2(project,id);
        return "redirect:/projects";
    }




    @RequestMapping("/projectdownload/{id}")
    public ResponseEntity<byte[]> projectdownload(@PathVariable("id")Integer id)throws IOException {
        Project project = projectService.findOne(id);
        String filePath = project.getPath();
        String[] strings = filePath.split("/");
        String name = strings[strings.length-1];
        File file = new File(filePath);
        //处理显示中文文件名的问题
        String fileName=new String(name.getBytes("utf-8"),"ISO-8859-1");
        //设置请求头内容,告诉浏览器代开下载窗口
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",fileName );
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity responseEntity = new ResponseEntity(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
        return responseEntity;
    }

}
