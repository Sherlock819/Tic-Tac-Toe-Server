package com.example.tictactoeserver.tictactoeserver.repo;

import com.example.tictactoeserver.tictactoeserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    User findByUserIdOrMobileOrEmail(Long userId, String mobile, String email);

    User findByMobileOrEmail(String mobile, String email);

    User findByEmail(String email);

    User findByMobile(String mobile);

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);
}
