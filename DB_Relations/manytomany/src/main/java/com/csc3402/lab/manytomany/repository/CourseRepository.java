package com.csc3402.lab.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.csc3402.lab.manytomany.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
