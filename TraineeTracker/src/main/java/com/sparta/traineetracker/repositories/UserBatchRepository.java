package com.sparta.traineetracker.repositories;
import com.sparta.traineetracker.entities.Batch;
import com.sparta.traineetracker.entities.Course;
import com.sparta.traineetracker.entities.User;
import com.sparta.traineetracker.entities.UserBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface UserBatchRepository extends JpaRepository<UserBatch, Integer> {

    List<UserBatch> findByBatch(Batch batch);
    List<UserBatch> findAllByBatch(Batch batch);
    UserBatch findByUser(User user);

    List<UserBatch>  findAllByUser(User user);
    List<User> findAllUserByBatch(Batch batch);

}