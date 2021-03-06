package com.sonkabin.service.impl;

import com.sonkabin.entity.PostManage;
import com.sonkabin.repository.PostManageRepository;
import com.sonkabin.service.PostManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
    public PostManage findOne(Integer id){
        Optional<PostManage> optional = postManageRepository.findById(id);
        PostManage postManage=optional.get();
        return postManage;
    }

    @Override
    public void savePostManage(PostManage postManage, MultipartFile file) throws IOException {//上传材料
        String fileName = file.getOriginalFilename();//获取文件名
        String filePath = "/Users/apple/Desktop/"+fileName;
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(file.getBytes());//上传
        outputStream.flush();
        outputStream.close();
        postManage.setPath(filePath);//将文件路径保存到数据库中
        postManage.setCreate(LocalDateTime.now());
        postManage.setUpdate(LocalDateTime.now());
        postManageRepository.save(postManage);//更新数据
    }
}
