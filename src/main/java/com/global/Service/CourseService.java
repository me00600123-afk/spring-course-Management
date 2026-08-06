package com.global.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.global.DTO.CourseDTO;
import com.global.Repo.CourseRepo;
import com.global.Repo.InstructorRepo;
import com.global.entity.Course;
import com.global.entity.Instructor;
import com.global.exception.NotFoundException;
import com.global.mapper.CourseMapper;
import com.global.page.PageResponse;
import com.global.search.CourseSearch;

@Service
public class CourseService {
	@Autowired
	CourseRepo courseRepo;
	@Autowired
	InstructorRepo instructorRepo;
	@Autowired
	MessageSource messageSource;
	@Autowired
	CourseMapper courseMapper;
	@Autowired
	CourseSearch search;
	
	
	@CacheEvict(value = "course" , allEntries = true)
	public Course insert(Course course) {
		Optional<Instructor> entity = instructorRepo.findById(course.getInstructor().getId());
		if(!entity.isPresent()) {
			throw new NotFoundException("Instructore not exist, cannot create course without instructore");
		}
		return courseRepo.save(course);
	}
	
	@Cacheable(value = "course" , key = "#pageNum + '-' + #pageSize + '-' + #colName + '-' + #isASC")
	public PageResponse<Course> findAll(int pageNum,int pageSize,String colName,boolean isASC) {
		Sort sort = Sort.by(isASC? Direction.ASC : Direction.DESC ,colName);
		Pageable pageable =PageRequest.of(pageNum, pageSize, sort);
		Page<Course> page = courseRepo.findAll(pageable);
		return PageResponse.from(page);
	}
	
	@Cacheable(value = "course" , key = "#id")
	public Course findById(Long id) {
		Optional<Course> course = courseRepo.findById(id);
		if(!course.isPresent()) {
//			String[] msg = {id.toString()};
//			String message = messageSource.getMessage("id.not.found",msg,LocaleContextHolder.getLocale());
			throw new NotFoundException("Invalid id =>" + id);
		}
		
		
		return course.get();
	}
	
	
	
	
	
	
	
	
	
	@CachePut(value = "course",key = "#course.id")
	public Course update(CourseDTO course) {
		Course entity = this.findById(course.getId());
		courseMapper.updateDTO(course, entity);
		return courseRepo.save(entity);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@CacheEvict(value = "course" , allEntries = true)
	public void delete(Long id) {
		
		Optional<Course> course = courseRepo.findById(id);
		if(!course.isPresent()) {
//			String[] msg = {id.toString()};
//			String message = messageSource.getMessage("id.not.found",msg,LocaleContextHolder.getLocale());
			throw new NotFoundException("Invalid id => " + id);
		}
		
		
		courseRepo.deleteById(id);
		
	}
	@Cacheable(value = "course", key = "#title + '-' + #level + '-' + #status + '-' + #minPrice + '-' + #maxPrice + '-' + #description")
	public List<Course> search(String title,String level,String status,Long minPrice , Long maxPrice,String description){
		Specification<Course> spec = Specification.unrestricted();
		if(title != null && !title.isBlank()) {
			spec = spec.and(search.searchByTitle(title));
		}
		if(level != null && !level.isBlank()) {
			spec = spec.and(search.searchByLevel(level));
		}
		if(status != null && !status.isBlank()) {
			spec = spec.and(search.searchByStatus(status));
		}
		if(minPrice != null && maxPrice != null ) {
			spec = spec.and(search.searchByPrice(minPrice,maxPrice));
		}
		if(description != null && !description.isBlank()) {
			spec = spec.and(search.searchByDescription(description));
		}
		return courseRepo.findAll(spec);
	}


}
