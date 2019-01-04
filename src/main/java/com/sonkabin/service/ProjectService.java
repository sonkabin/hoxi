package com.sonkabin.service;


import com.sonkabin.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();

    void saveProject(Project project);

    Project findOne(Integer id);
}
