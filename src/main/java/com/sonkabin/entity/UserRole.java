package com.sonkabin.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tb_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_name",length = 20)
    private String name;
    @Column
    private String description;
    @Column(name = "gmt_create")
    private LocalDateTime create;
    @Column(name = "gmt_modified")
    private LocalDateTime update;

    private String note;

    @ManyToMany(mappedBy = "roles")
    private Set<Menu> menus;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", create=" + create +
                ", update=" + update +
                ", note='" + note + '\'' +
                '}';
    }

    public UserRole() {
    }


}
