package com.DietfriendsTodoDemoApp.domain.user.repository;

import com.DietfriendsTodoDemoApp.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);

    Optional<User> findUserByUserName(String userName);

}
