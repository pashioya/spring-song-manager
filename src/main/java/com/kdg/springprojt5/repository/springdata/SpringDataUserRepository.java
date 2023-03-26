package com.kdg.springprojt5.repository.springdata;

import com.kdg.springprojt5.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("springData")
public interface SpringDataUserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
