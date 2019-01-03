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
    @Query(value = "select u from User u inner join fetch u.dept d inner join fetch u.role r order by u.id")
    List<User> findAllWithDetails();

}
