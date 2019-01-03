package com.sonkabin.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

//菜单
@Entity
@Table(name = "tb_menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "menu_name",length = 40)
    private String name;//菜单名
    @Column(name = "gmt_create")
    private LocalDateTime create;
    @Column(name = "gmt_modified")
    private LocalDateTime update;

    @ManyToMany
    @JoinTable(name = "tb_authority",joinColumns = @JoinColumn(name="menu_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreate() {
        return create;
    }

    public void setCreate(LocalDateTime create) {
        this.create = create;
    }

    public LocalDateTime getUpdate() {
        return update;
    }

    public void setUpdate(LocalDateTime update) {
        this.update = update;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Menu() {
    }
}
