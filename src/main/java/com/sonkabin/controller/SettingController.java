package com.sonkabin.controller;

import com.sonkabin.entity.Menu;
import com.sonkabin.entity.UserRole;
import com.sonkabin.service.MenuService;
import com.sonkabin.service.UserRoleService;
import com.sonkabin.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Controller
public class SettingController {

    @Autowired
    private UserRoleService roleService;
    @Autowired
    private MenuService menuService;

    @ModelAttribute
    public void modelAttribute(@RequestParam(name = "roleId",required = false)Integer roleId,
                               @RequestParam(name = "menuId",required = false)Integer menuId,
                               Map<String,Object> map){
        if(!ObjectUtils.isEmpty(roleId)){
            UserRole role = roleService.findById(roleId);
            map.put("role",role);
        }
        if(!ObjectUtils.isEmpty(menuId)){
            Menu menu = menuService.findById(menuId);
            map.put("menu",menu);
        }
    }

    @GetMapping("/settings")
    public String settings(Model model){
        List<UserRole> roles = roleService.findAll();
        List<Menu> menus = menuService.findAll();
        Object[] authority = roleService.findAllAuthority();
        model.addAttribute("roles",roles);
        model.addAttribute("menus",menus);
        model.addAttribute("authorities",authority);
        return "setting";
    }


    @GetMapping("/role")
    public String toAddRolePage(){
        return "role/add";
    }


    @PostMapping("/role")
    public String addRole(UserRole role){
        role.setUpdate(LocalDateTime.now());
        role.setCreate(LocalDateTime.now());
        roleService.saveRole(role);
        return "redirect:/settings";
    }

    @GetMapping("/role/{id}")
    public String toEditRolePage(@PathVariable("id")Integer id,Model model){
        UserRole role = roleService.findById(id);
        model.addAttribute("role",role);
        return "role/add";
    }

    @PutMapping("/role")
    public String updateRole(UserRole role){
        role.setUpdate(LocalDateTime.now());
        roleService.saveRole(role);
        return "redirect:/settings";
    }

    @DeleteMapping("/role/{id}")
    public String deleteRole(@PathVariable("id")Integer id){
        roleService.deleteRole(id);
        return "redirect:/settings";
    }

    @GetMapping("/menu")
    public String toAddMenuPage(){
        return "menu/add";
    }

    @PostMapping("/menu")
    public String addMenu(Menu menu){
        menu.setCreate(LocalDateTime.now());
        menu.setUpdate(LocalDateTime.now());
        menuService.saveMenu(menu);
        return "redirect:/settings";
    }

    @GetMapping("/menu/{id}")
    public String toEditMenuPage(@PathVariable("id")Integer id,Model model){
        Menu menu = menuService.findById(id);
        model.addAttribute("menu",menu);
        return "menu/add";
    }

    @PutMapping("/menu")
    public String updateMenu(Menu menu){
        menu.setUpdate(LocalDateTime.now());
        menuService.saveMenu(menu);
        return "redirect:/settings";
    }

    @DeleteMapping("/menu/{id}")
    public String deleteMenu(@PathVariable("id")Integer id){
        menuService.deleteMenu(id);
        return "redirect:/settings";
    }

    @GetMapping("/authority")
    public String toAddAuthorityPage(Model model){
        //查询角色，
        List<UserRole> roles = roleService.findAll();
        model.addAttribute("roles",roles);
        return "authority/add";
    }

    /**
     * 选择不同的角色时，查询还未添加的菜单
     * @return
     */
    @ResponseBody
    @RequestMapping("/menus")
    public Object[] getMenus(@RequestParam(name = "roleId")Integer id){
        Object[]menus = menuService.findAllWithoutRole(id);
        return menus;
    }

    @PostMapping("/authority")
    public String addAuthority(@RequestParam(name = "roleId")Integer roleId,
                               @RequestParam(name = "menuId")Integer menuId){
        roleService.saveAuthority(roleId,menuId);
        return "redirect:/settings";
    }

    @DeleteMapping("/authority")
    public String deleteAuthority(@RequestParam(name = "roleId")Integer roleId,
                                  @RequestParam(name = "menuId")Integer menuId){
        roleService.deleteAuthority(roleId,menuId);
        return "redirect:/settings";
    }
}
