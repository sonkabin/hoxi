package com.sonkabin.repository;


import com.sonkabin.entity.MidManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidManageRepository extends JpaRepository<MidManage,Integer> {

    //    //三表查询
//    @Query(value = "select p from MidManage p order by u.id")
//    List<MidManage> findAllWithDetails();
}
