package com.sonkabin.service;

import com.sonkabin.entity.MidManage;

import java.util.List;

public interface MidManageService {
    List<MidManage> findAll();

    void saveMidManage(MidManage midManage);

    MidManage findOne(Integer id);
}
