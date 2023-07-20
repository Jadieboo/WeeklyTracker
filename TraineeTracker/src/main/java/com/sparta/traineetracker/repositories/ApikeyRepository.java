package com.sparta.traineetracker.repositories;

import com.sparta.traineetracker.entities.Apikey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApikeyRepository extends JpaRepository<Apikey, Integer> {

    Apikey findByApikey(String key);

}