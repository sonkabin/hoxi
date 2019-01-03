package com.sonkabin.service;

import com.sonkabin.entity.PreManage;

import java.util.List;

public interface PreManageService {
    List<PreManage> findAll();

    void savePreManage(PreManage preManage);

    PreManage findOne(Integer id);
}
