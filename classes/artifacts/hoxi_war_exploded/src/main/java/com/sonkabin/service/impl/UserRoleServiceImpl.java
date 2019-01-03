package com.sonkabin.service.impl;

import com.sonkabin.entity.UserRole;
import com.sonkabin.repository.UserRoleRepository;
import com.sonkabin.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository roleRepository;


    @Override
    public List<UserRole> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(UserRole role) {
        role.setCreate(LocalDateTime.now());
        role.setUpdate(LocalDateTime.now());
        roleRepository.save(role);
    }

    @Override
    public UserRole findById(Integer id) {
        Optional<UserRole> optional = roleRepository.findById(id);
        UserRole role = optional.get();
        return role;
    }
}
