package com.sonkabin.service.impl;

import com.sonkabin.entity.PostManage;
import com.sonkabin.repository.PostManageRepository;
import com.sonkabin.service.PostManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostManageImpl implements PostManageService {

    @Autowired
    private PostManageRepository postManageRepository;


    @Override
    public List<PostManage> findAll() {
        return postManageRepository.findAllWithDetails();
    }
    @Override
    public  void savePostManage(PostManage postManage){
        postManage.setCreate(LocalDateTime.now());
        postManage.setUpdate(LocalDateTime.now());
        postManageRepository.save(postManage);
    }

    @Override
    public PostManage findById(Integer id){
        Optional<PostManage> optional = postManageRepository.findById(id);
        PostManage postManage = optional.get();
        return postManage;
    }



}
