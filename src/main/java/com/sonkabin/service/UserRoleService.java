package com.sonkabin.service;


import com.sonkabin.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> findAll();

    void saveRole(UserRole role);

    UserRole findById(Integer id);
}
