package com.sonkabin.repository;

import com.sonkabin.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    long countByUserId(Integer id);

    @Query(value = "SELECT m FROM Message m WHERE m.user.id = ?1 ORDER BY m.status,m.create DESC ")
    List<Message> findAllMsgById(Integer id);

    @Modifying
    @Query(value = "UPDATE Message m SET m.status = 1 WHERE m.id=?1")
    void marked(Integer id);
}
