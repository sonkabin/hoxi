package com.sonkabin.repository;

import com.sonkabin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {

    @Query(value = "SELECT mm.id,mm.name FROM Menu mm WHERE mm.id NOT IN (SELECT m.id FROM Menu m INNER JOIN m.roles r WHERE r.id=?1)")
    Object[] findAllWithRole(Integer id);
}
