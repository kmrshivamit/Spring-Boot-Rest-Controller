package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CourseDao;
import com.example.demo.entities.Course;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	public List<Course> getCourses() {
		return courseDao.findAll();
	}

	@Override
	public Course getCourse(long id) {

		return courseDao.findById(id).get();
	}

	@Override
	public Course addCourse(Course course) {
		courseDao.save(course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
		courseDao.save(course);
		return course;
	}

	@Override
	public void deleteCourse(long id) {
		Course course = null;
		try {
		course = courseDao.getOne(id);
		courseDao.delete(course);
		System.out.println(course);
		if(course==null)
			throw new EntityNotFoundException();
			
		}catch(EntityNotFoundException e)
		{
			throw e;
		}		
		
	}

	@Override
	public List<Course> findByTitle(String title) {
		return courseDao.findByTitle(title);
	}

}
