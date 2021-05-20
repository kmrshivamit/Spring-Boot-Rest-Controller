package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Course;

public interface CourseDao extends JpaRepository<Course, Long>{
	List<Course> findByTitle(String title);
	

}
