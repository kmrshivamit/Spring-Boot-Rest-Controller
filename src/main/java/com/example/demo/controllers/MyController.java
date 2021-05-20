package com.example.demo.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Course;
import com.example.demo.services.CourseService;

@RestController

public class MyController {
	@Autowired
	private CourseService courseService;

	@GetMapping("/home")
	public String home() {
		return "this is home controller";
	}

	// get the courses
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getCourses() {
		return new ResponseEntity<>(courseService.getCourses(),HttpStatus.OK);

	}

	// get course using id
	@GetMapping("/courses/{courseId}")
	public Course getCourse(@PathVariable String courseId) {
		return this.courseService.getCourse(Long.parseLong(courseId));

	}
	
	@GetMapping("/title/{title}")
	public List<Course> getCourseByTitle(@PathVariable String title)
	{
		return this.courseService.findByTitle(title);
		
	}

	// add course
	
	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course course) {
		return this.courseService.addCourse(course);

	}

	// update course using put request
	@PutMapping("/courses")
	public Course updateCourse(@RequestBody Course course) {
		
		return this.courseService.updateCourse(course);

	}

	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId) {
		try {
			this.courseService.deleteCourse(Long.parseLong(courseId));
		
			return new ResponseEntity<>(HttpStatus.OK);
		} catch( EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String exceptonHandlerNull()
	{
		return "There is null pointer exception"; 
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public String exceptonHandlerEnityNotFound()
	{
		return "Entity Not found"; 
	}

	
}
