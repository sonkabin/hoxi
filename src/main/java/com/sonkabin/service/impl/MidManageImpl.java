package com.sonkabin.service.impl;


import com.sonkabin.entity.MidManage;
import com.sonkabin.entity.PostManage;
import com.sonkabin.repository.MidManageRepository;
import com.sonkabin.repository.PostManageRepository;
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
    @Autowired
    private PostManageRepository postManageRepository;

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
        if(midManage.getState().equals("1")){
            PostManage postManage = new PostManage();
            postManage.setState("0");
            postManage.setProject(midManage.getProject());
            postManage.setCreate(LocalDateTime.now());
            postManageRepository.save(postManage);
        }
    }
    @Override
    public  MidManage findOne(Integer id){
        Optional<MidManage> optional = midManageRepository.findById(id);
        MidManage midManage = optional.get();
        return midManage;
    }

}
