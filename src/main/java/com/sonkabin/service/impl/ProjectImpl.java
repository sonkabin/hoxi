package com.sonkabin.service.impl;

import com.sonkabin.entity.Project;
import com.sonkabin.service.ProjectService;
import com.sonkabin.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        List<Project> projectList = projectRepository.findAllWithDetails();
        return projectList;
    }


    @Override
    public void saveProject(Project project) {
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