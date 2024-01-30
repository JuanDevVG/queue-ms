package com.juandev.queuems.service;

import com.juandev.queuems.model.User;
import com.juandev.queuems.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }


    public Optional<User> findByIdentityCard(String identityCard) {
        return userRepository.findByIdentityCard(identityCard);
    }

    public User inactivateUser(String identityCard) {
        Optional<User> optionalUser = userRepository.findByIdentityCard(identityCard);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}