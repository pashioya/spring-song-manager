package com.kdg.springprojt5.repository;

import com.kdg.springprojt5.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
