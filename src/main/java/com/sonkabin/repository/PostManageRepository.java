package com.sonkabin.repository;

import com.sonkabin.entity.PostManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostManageRepository extends JpaRepository<PostManage,Integer> {

//    //三表查询
//    @Query(value = "select p from PostManage p order by u.id")
//    List<PostManage> findAllWithDetails();
}
