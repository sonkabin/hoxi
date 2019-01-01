package com.sonkabin.repository;

import com.sonkabin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {

    @Query(value = "SELECT m FROM Menu m INNER JOIN FETCH m.roles r WHERE r.id<>?1")
    List<Menu> findAllWithoutRole(Integer id);
}
