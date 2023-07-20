package com.sparta.traineetracker.repositories;

import com.sparta.traineetracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByFirstNameAndLastName(String fname,String lname);

}