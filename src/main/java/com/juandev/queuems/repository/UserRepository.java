package com.juandev.queuems.repository;

import com.juandev.queuems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByIdentityCard(String identityCard);

    Optional<User> findByUsername(String username);
}
