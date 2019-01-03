package com.sonkabin.service.impl;

import com.sonkabin.entity.PreManage;
import com.sonkabin.repository.PreManageRepository;
import com.sonkabin.service.PreManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PreManageImpl implements PreManageService {
    @Autowired
    private  PreManageRepository preManageRepository;


    @Override
    public List<PreManage> findAll(){
        List<PreManage> preManages=preManageRepository.findAllWithDetails();
        return preManages;
    }
    @Override
    public void savePreManage(PreManage preManage){
        preManage.setCreate(LocalDateTime.now());
        preManage.setUpdate(LocalDateTime.now());
        preManageRepository.save(preManage);
    }

    @Override
    public  PreManage findOne(Integer id){
        Optional<PreManage> optional =preManageRepository.findById(id);
        PreManage preManage=optional.get();
        return preManage;
    }
}
