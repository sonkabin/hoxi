package com.sonkabin.service.impl;

import com.sonkabin.entity.User;
import com.sonkabin.repository.UserRepository;
import com.sonkabin.service.UserService;
import com.sonkabin.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(String username, String password) {
        User user = userRepository.findUserByNameAndPassword(username, password);
        if(!ObjectUtils.isEmpty(user)){
            logger.trace(user.getName());
            return true;
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAllWithDetails();
        return users;
    }

    @Override
    public void saveUser(User user) {

        userRepository.save(user);
    }

    @Override
    public User findOne(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        User user = optional.get();
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
