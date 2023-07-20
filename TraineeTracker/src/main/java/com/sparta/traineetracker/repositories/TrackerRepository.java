package com.sparta.traineetracker.repositories;

import com.sparta.traineetracker.entities.Tracker;
import com.sparta.traineetracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackerRepository extends JpaRepository<Tracker, Integer> {


    List<Tracker> findByTrainee(User user);

    List<Tracker> findAllByTrainee(User user);

    Tracker findByTraineeAndWeek(User user, int week);

}