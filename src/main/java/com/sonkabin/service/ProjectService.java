package com.sonkabin.service;


import com.sonkabin.entity.Project;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProjectService {
    List<Project> findAll();

    void saveProject(Project project);

    void saveProject2(Project project, Integer id);

    Project findOne(Integer id);

    void saveProject(Project project,MultipartFile file) throws IOException;
}
