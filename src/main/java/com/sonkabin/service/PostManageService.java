package com.sonkabin.service;

import com.sonkabin.entity.PostManage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostManageService {
    List<PostManage> findAll();

    void savePostManage(PostManage postManage);

    PostManage findOne(Integer id);

    void savePostManage(PostManage postManage, MultipartFile file) throws IOException;

}
