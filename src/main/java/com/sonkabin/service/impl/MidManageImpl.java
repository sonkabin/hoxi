package com.sonkabin.service.impl;


import com.sonkabin.entity.MidManage;
import com.sonkabin.repository.MidManageRepository;
import com.sonkabin.service.MidManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MidManageImpl implements MidManageService {

    @Autowired
    private MidManageRepository midManageRepository;

    @Override
    public List<MidManage> findAll() {
        return midManageRepository.findAll();
    }
}
