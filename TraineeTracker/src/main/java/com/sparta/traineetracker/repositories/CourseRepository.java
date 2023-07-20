package com.sparta.traineetracker.repositories;

import com.sparta.traineetracker.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findByCourseName(String courseName);
}