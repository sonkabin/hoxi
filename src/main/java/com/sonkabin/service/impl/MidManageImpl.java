package com.sonkabin.service.impl;


import com.sonkabin.entity.MidManage;
import com.sonkabin.repository.MidManageRepository;
import com.sonkabin.service.MidManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MidManageImpl implements MidManageService {

    @Autowired
    private MidManageRepository midManageRepository;

    @Override
    public List<MidManage> findAll() {
        List<MidManage> midManages = midManageRepository.findAllWithDetails();
        return midManages;
    }

    @Override
    public void saveMidManage(MidManage midManage){
        midManage.setCreate(LocalDateTime.now());
        midManage.setUpdate(LocalDateTime.now());
        midManageRepository.save(midManage);
    }
    @Override
    public  MidManage findOne(Integer id){
        Optional<MidManage> optional = midManageRepository.findById(id);
        MidManage midManage = optional.get();
        return midManage;
    }

}
