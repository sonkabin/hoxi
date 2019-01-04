package com.sonkabin.controller;

import com.sonkabin.entity.MidManage;
import com.sonkabin.entity.PreManage;
import com.sonkabin.entity.Project;
import com.sonkabin.service.PreManageService;
import com.sonkabin.service.ProjectService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.io.IOException;
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

    @GetMapping("/exppreManages")
    public String toExpertListPage(Model model){
        List<PreManage> preManages=preManageService.findAll();
        model.addAttribute("preManages",preManages);
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects",projects);
        return "PreManage/expertlist";
    }



    @GetMapping("/preManage/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        PreManage preManage=preManageService.findOne(id);
        model.addAttribute("preManage",preManage);
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects",projects);
        return "PreManage/edit";
    }

    @PutMapping("/preManage")
    public String updatePreManage(PreManage preManage){
        preManageService.savePreManage(preManage);
        return "redirect:/exppreManages";
    }

    @GetMapping("/setRules")
    public String toShowPage(){
        return "PreManage/SetRules";
    }


    @RequestMapping("/predownload/{id}")
    public ResponseEntity<byte[]> middownload(@PathVariable("id")Integer id)throws IOException {
        PreManage preManage = preManageService.findOne(id);
        String filePath = preManage.getPath();
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
