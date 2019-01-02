package com.sonkabin.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
//@Table(name = "tb_authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = UserRole.class)
    @JoinColumn(name = "role_id")
    private UserRole role;

    @ManyToOne(targetEntity = Menu.class)
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @Column(name = "gmt_create")
    private LocalDateTime create;
    @Column(name = "gmt_modified")
    private LocalDateTime update;


}
