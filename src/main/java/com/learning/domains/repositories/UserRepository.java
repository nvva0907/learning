package com.learning.domains.repositories;

import com.learning.domains.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String email);
}
