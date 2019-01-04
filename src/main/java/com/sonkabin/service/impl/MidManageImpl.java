package com.sonkabin.service.impl;


import com.sonkabin.entity.MidManage;
import com.sonkabin.entity.PostManage;
import com.sonkabin.entity.Project;
import com.sonkabin.entity.User;
import com.sonkabin.repository.MidManageRepository;
import com.sonkabin.repository.PostManageRepository;
import com.sonkabin.repository.ProjectRepository;
import com.sonkabin.service.MidManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MidManageImpl implements MidManageService {

    @Autowired
    private MidManageRepository midManageRepository;
    @Autowired
    private PostManageRepository postManageRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<MidManage> findAll() {
        List<MidManage> midManages = midManageRepository.findAllWithDetails();
        return midManages;
    }

    @Override
    public void saveMidManage(MidManage midManage) {
        midManage.setCreate(LocalDateTime.now());
        midManage.setUpdate(LocalDateTime.now());
        midManageRepository.save(midManage);
        if (midManage.getState().equals("1")) {
            PostManage postManage = new PostManage();
            postManage.setState("0");
            postManage.setProject(midManage.getProject());
            postManage.setCreate(LocalDateTime.now());
            postManageRepository.save(postManage);
        }
    }

    @Override
    public void savetecMidManage(MidManage midManage, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\pppa\\Desktop\\" + fileName;
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        midManage.setPath(filePath);
        midManage.setCreate(LocalDateTime.now());
        midManage.setUpdate(LocalDateTime.now());
        midManageRepository.save(midManage);
        if (midManage.getState().equals("1")) {
            PostManage postManage = new PostManage();
            postManage.setState("0");
            Integer proId = midManage.getProject().getId();
            Project project = projectRepository.getOne(proId);
            postManage.setProject(project);
            postManage.setCreate(LocalDateTime.now());
            postManageRepository.save(postManage);
            project.setStatus(5);
            projectRepository.save(project);
        }
    }

    @Override
    public void saveMidManage(MidManage midManage, MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\pppa\\Desktop\\"+fileName;
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();
        midManage.setMidmaterial(filePath);
        midManage.setCreate(LocalDateTime.now());
        midManage.setUpdate(LocalDateTime.now());
        midManageRepository.save(midManage);
        if(midManage.getState().equals("1")){
            PostManage postManage = new PostManage();
            postManage.setState("0");
            Integer proId = midManage.getProject().getId();
            Project project = projectRepository.getOne(proId);
            postManage.setProject(project);
            postManage.setCreate(LocalDateTime.now());
            postManageRepository.save(postManage);
            project.setStatus(5);
            projectRepository.save(project);
        }
    }
    @Override
    public  MidManage findOne(Integer id){
        Optional<MidManage> optional = midManageRepository.findById(id);
        MidManage midManage = optional.get();
        return midManage;
    }

}
