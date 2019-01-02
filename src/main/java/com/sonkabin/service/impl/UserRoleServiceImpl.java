package com.sonkabin.service.impl;

import com.sonkabin.entity.User;
import com.sonkabin.entity.UserRole;
import com.sonkabin.repository.UserRepository;
import com.sonkabin.repository.UserRoleRepository;
import com.sonkabin.service.UserRoleService;
import com.sonkabin.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<UserRole> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(UserRole role) {
        roleRepository.save(role);
    }

    @Override
    public UserRole findById(Integer id) {
        Optional<UserRole> optional = roleRepository.findById(id);
        UserRole role = optional.get();
        return role;
    }

    @Override
    public void deleteRole(Integer id) {
        List<User> users = userRepository.findAllWithRoleId(id);
        for (User user : users) {
            user.setRole(null);
        }
        roleRepository.deleteById(id);
    }
}
