package com.sonkabin.service.impl;

import com.sonkabin.entity.Menu;
import com.sonkabin.entity.UserRole;
import com.sonkabin.repository.MenuRepository;
import com.sonkabin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public void saveMenu(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public Menu findById(Integer id) {
        Optional<Menu> optional = menuRepository.findById(id);
        return optional.get();
    }

    @Override
    public void deleteMenu(Integer id) {
        menuRepository.deleteById(id);
    }

    @Override
    public Object[] findAllWithoutRole(Integer id) {
        Object[] menus = menuRepository.findAllWithRole(id);
        return menus;
    }

    @Override
    public void saveAuthority(Integer roleId, Integer menuId) {
        Menu menu = new Menu();
        UserRole role = new UserRole();
        role.setId(roleId);
        menu.setId(menuId);
        menu.setRoles(Collections.singleton(role));
        menuRepository.save(menu);
    }
}
