package com.sonkabin.repository;

import com.sonkabin.entity.PreManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreManageRepository extends JpaRepository<PreManage,Integer> {

    @Query(value="select p from PreManage p inner join fetch p.project pp where p.id is not null order by p.id")
    List<PreManage> findAllWithDetails();
}
