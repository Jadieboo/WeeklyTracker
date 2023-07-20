package com.sparta.traineetracker.repositories;

import com.sparta.traineetracker.entities.Batch;
import com.sparta.traineetracker.entities.Course;
import com.sparta.traineetracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Integer> {


    Batch findByBatchName(String name);

}