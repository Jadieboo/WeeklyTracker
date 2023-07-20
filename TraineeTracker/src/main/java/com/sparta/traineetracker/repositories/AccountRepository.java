package com.sparta.traineetracker.repositories;

import com.sparta.traineetracker.entities.Account;
import com.sparta.traineetracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByUser(User user);
    Optional<Account> findByUsername(String username);


}