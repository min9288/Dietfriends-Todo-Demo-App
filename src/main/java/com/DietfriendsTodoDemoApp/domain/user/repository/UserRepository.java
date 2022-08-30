package com.DietfriendsTodoDemoApp.domain.user.repository;

import com.DietfriendsTodoDemoApp.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserId(UUID userId);
}
