package com.sonkabin.service;

import com.sonkabin.entity.PreManage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PreManageService {
    List<PreManage> findAll();

    void savePreManage(PreManage preManage);

    void savePreManage(PreManage preManage, MultipartFile file) throws IOException;

    PreManage findOne(Integer id);
}
