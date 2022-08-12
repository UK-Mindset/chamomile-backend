package com.kimsongnam.mindset.entity.user.repository;

import com.kimsongnam.mindset.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserUsername(String userUsername);
    boolean existsByUserPhone(String userPhone);

    Optional<User> findByUserEmail(String userEmail);
}
