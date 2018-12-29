package com.sonkabin.service;

import com.sonkabin.entity.PostManage;

import java.util.List;

public interface PostManageService {
    List<PostManage> findAll();

    void savePostManage(PostManage postManage);

    PostManage findById(Integer id);
}
