package com.sonkabin.repository;

import com.sonkabin.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

   @Query(value = "select p from Project p inner join fetch p.user pp where p.id is not null order by p.id")
   List<Project> findAllWithDetails();
}
