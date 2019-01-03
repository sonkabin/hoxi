package com.sonkabin.service.impl;

import com.sonkabin.entity.PreManage;
import com.sonkabin.repository.PreManageRepository;
import com.sonkabin.service.PreManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreManageImpl implements PreManageService {
    @Autowired
    private  PreManageRepository preManageRepository;

    @Override
    public List<PreManage> findAll(){
        return preManageRepository.findAll();
    }
}
