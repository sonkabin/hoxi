package com.sonkabin.service;

import com.sonkabin.entity.Menu;
import com.sonkabin.entity.UserRole;

import java.util.List;

public interface MenuService {
    List<Menu> findAll();

    void saveMenu(Menu menu);

    Menu findById(Integer id);

    void deleteMenu(Integer id);

    List<Menu> findAllWithoutRole(Integer id);
}
