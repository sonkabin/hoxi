package com.sonkabin.repository;

import com.sonkabin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    //select * from user where user = username and password = password
    User findUserByNameAndPassword(String username,String password);

    //三表连接
    @Query(value = "SELECT u FROM User u INNER JOIN FETCH u.dept d INNER JOIN FETCH u.role r ORDER BY u.id")
    List<User> findAllWithDetails();

    @Query(value = "SELECT u FROM User u WHERE u.role.id = ?1")
    List<User> findAllWithRoleId(Integer id);

}
