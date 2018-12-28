package com.sonkabin.service.impl;

import com.sonkabin.entity.PostManage;
import com.sonkabin.repository.PostManageRepository;
import com.sonkabin.service.PostManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostManageImpl implements PostManageService {

    @Autowired
    private PostManageRepository postManageRepository;


    @Override
    public List<PostManage> findAll() {
        return postManageRepository.findAll();
    }
}
