package com.sonkabin.service.impl;

import com.sonkabin.entity.MidManage;
import com.sonkabin.entity.PreManage;
import com.sonkabin.repository.MidManageRepository;
import com.sonkabin.repository.PreManageRepository;
import com.sonkabin.service.PreManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PreManageImpl implements PreManageService {
    @Autowired
    private  PreManageRepository preManageRepository;
    @Autowired
    private MidManageRepository midManageRepository;


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
        if(preManage.getProject().getStatus() == 2){
            MidManage midManage = new MidManage();
            midManage.setState("0");
            midManage.setProject(preManage.getProject());
            midManage.setCreate(LocalDateTime.now());
            midManageRepository.save(midManage);
        }
    }

    @Override
    public void savePreManage(PreManage preManage, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\pppa\\Desktop\\" + fileName;
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        preManage.setPath(filePath);
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
