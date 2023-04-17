package com.learning.domains.repositories;

import com.learning.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhoneNumber(String phoneNumber);
}
