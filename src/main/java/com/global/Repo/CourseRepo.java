package com.global.Repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.global.entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long>,JpaSpecificationExecutor<Course>{
	
	public Page<Course> findAll(Pageable page);
	
	public Optional<Course> findByInstructorId(Long id);

}
