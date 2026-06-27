package com.csc3402.lab.manytomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.csc3402.lab.manytomany.model.Course;
import com.csc3402.lab.manytomany.model.Student;
import com.csc3402.lab.manytomany.repository.CourseRepository;
import com.csc3402.lab.manytomany.repository.StudentRepository;

import org.springframework.boot.CommandLineRunner;
import java.util.Arrays;

@SpringBootApplication
public class ManytomanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManytomanyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentRepository studentRepository, CourseRepository courseRepository) {
		return args -> {
			// Create some courses
			Course course1 = new Course("Introduction to Computer Science", "CS101", 10, 500.0);
			Course course2 = new Course("Data Structures and Algorithms", "CS201", 15, 700.0);
			Course course3 = new Course("Database Systems", "CS301", 12, 600.0);

			courseRepository.saveAll(Arrays.asList(course1, course2, course3));

			// Create some students
			Student student1 = new Student("Alice", 20, "A");
			Student student2 = new Student("Bob", 22, "B");
			Student student3 = new Student("Charlie", 21, "A");

			// Enroll students in courses
			student1.getCourses().addAll(Arrays.asList(course1, course2, course3));
			student2.getCourses().addAll(Arrays.asList(course1, course2));
			student3.getCourses().addAll(Arrays.asList(course1));

			studentRepository.save(student1);
			studentRepository.save(student2);
			studentRepository.save(student3);
		};
	}

}
