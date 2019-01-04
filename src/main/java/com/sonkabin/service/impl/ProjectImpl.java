package com.sonkabin.service.impl;

import com.sonkabin.entity.PreManage;
import com.sonkabin.entity.Project;
import com.sonkabin.entity.User;
import com.sonkabin.repository.PreManageRepository;
import com.sonkabin.repository.UserRepository;
import com.sonkabin.service.ProjectService;
import com.sonkabin.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PreManageRepository preManageRepository;

    @Override
    public List<Project> findAll() {
        List<Project> projectList = projectRepository.findAllWithDetails();
        return projectList;
    }


    @Override
    public void saveProject(Project project) {
        project.setCreate(LocalDateTime.now());
        project.setUpdate(LocalDateTime.now());
        Integer id = project.getUser().getId();
        Optional<User> optional = userRepository.findById(id);
        User user = optional.get();
        project.setUser(user);
        project.setUserName(user.getName());
        projectRepository.save(project);
        if(project.getStatus()== null){
            project.setStatus(5) ;
        }

    }

    @Override
    public void saveProject2(Project project, Integer id) {
        project.setCreate(LocalDateTime.now());
        project.setUpdate(LocalDateTime.now());
        User user = userRepository.getOne(id);
        project.setUser(user);
        projectRepository.save(project);
        if(project.getStatus()== null){
            project.setStatus(5) ;
        }
        if(project.getStatus() == 0){
            PreManage preManage = new PreManage();
            preManage.setProject(project);
            preManage.setCreate(LocalDateTime.now());
            preManageRepository.save(preManage);
        }

    }

    @Override
    public void saveProject(Project project, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\FMD\\Desktop\\" + fileName;
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        project.setPath(filePath);
        project.setCreate(LocalDateTime.now());
        project.setUpdate(LocalDateTime.now());
        projectRepository.save(project);
    }

    @Override
    public Project findOne(Integer id) {
        Optional<Project> optional = projectRepository.findById(id);
        Project project = optional.get();
        return project;
    }

}