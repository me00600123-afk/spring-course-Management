package com.global.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.global.DTO.CourseDTO;
import com.global.Repo.CourseRepo;
import com.global.Repo.InstructorRepo;
import com.global.Service.CourseService;
import com.global.entity.Course;
import com.global.entity.Instructor;
import com.global.exception.NotFoundException;
import com.global.mapper.CourseMapper;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
	@InjectMocks
	CourseService courseService;
	@Mock
	CourseRepo courseRepo; 
	@Mock
	CourseMapper courseMapper;
	@Mock
	InstructorRepo instructorRepo;
	
	@Test
	public void findByIFound() {
	Course course = new Course();
	course.setId(6L);
	Mockito.when(courseRepo.findById(6L))
	.thenReturn(Optional.of(course));
	Optional<Course> entity = courseRepo.findById(6L);
	assertEquals(entity.get(),course);
	Mockito.verify(courseRepo).findById(6L);
	}
	
	@Test
	public void findByINotFound() {
		Mockito.when(courseRepo.findById(1L)).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> courseService.findById(1L));
		Mockito.verify(courseRepo).findById(1L);
	}
	
	
	@Test
	public void insert() {
		 Instructor instructor = new Instructor();
		    instructor.setId(1L);

		    Course course = new Course();
		    course.setInstructor(instructor);

		  Mockito.when(instructorRepo.findById(1L))
		            .thenReturn(Optional.of(instructor));

		  Mockito.when(courseRepo.save(course))
		            .thenReturn(course);

		    Course result = courseService.insert(course);

		    assertEquals(course, result);
		    Mockito.verify(instructorRepo).findById(1L);
		    Mockito.verify(courseRepo).save(course);
	}
	
	
	
	@Test
	public void update() {
		Course course = new Course();
		course.setId(1L);
		 CourseDTO courseDTO = new CourseDTO();
		 courseDTO.setId(1L);
		 
		Mockito.when(courseRepo.findById(1L)).thenReturn(Optional.of(course));
		Mockito.when(courseRepo.save(course)).thenReturn(course);
		Course result = courseService.update(courseDTO);
		assertEquals(result,course);
		
		Mockito.verify(courseRepo).findById(1L);
		Mockito.verify(courseMapper).updateDTO(courseDTO,course);
		Mockito.verify(courseRepo).save(course);
	}
	
	
	
	
	@Test
	public void deleteCourseFound() {
		Course course = new Course();
		course.setId(1L);
		Mockito.when(courseRepo.findById(1L)).thenReturn(Optional.of(course));
		courseService.delete(1L);
		Mockito.verify(courseRepo).findById(1L);
		Mockito.verify(courseRepo).deleteById(1L);
	}
	
	@Test
	public void deleteCourseNotFound() {
		Mockito.when(courseRepo.findById(1L)).thenReturn(Optional.empty());
		assertThrows(NotFoundException.class, () -> courseService.delete(1L));
//		Mockito.verify(courseRepo).findById(1L);
//		Mockito.verify(courseRepo,Mockito.never()).deleteById(Mockito.any());
//		
	}
	
	

}
