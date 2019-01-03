package com.sonkabin.service.impl;

import com.sonkabin.entity.Department;
import com.sonkabin.repository.DepartmentRepository;
import com.sonkabin.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
