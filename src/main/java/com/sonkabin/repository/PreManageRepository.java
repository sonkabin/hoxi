package com.sonkabin.repository;

import com.sonkabin.entity.PreManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreManageRepository extends JpaRepository<PreManage,Integer> {

}
