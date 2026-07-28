package com.global.search;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.global.entity.Course;

@Component
public class CourseSearch {
	
	public Specification<Course> searchByTitle(String title){
		
		return (root,query,cb) -> cb.like(root.get("title"),"%" + title + "%");
		
	}
	
	
	public Specification<Course> searchByLevel(String level){
		return (root,query,cb)-> cb.like(root.get("level"),"%" + level + "%");
	}
	

	public Specification<Course> searchByStatus(String status){
		return (root,query,cb)-> cb.like(root.get("status"),"%" + status + "%");
	}
	
	

	public Specification<Course> searchByPrice(Long minPrice , Long maxPrice){
		return (root,query,cb)-> cb.between(root.get("price"),minPrice,maxPrice);
	}
	
	

	public Specification<Course> searchByDescription(String description){
		return (root,query,cb)-> cb.like(root.get("description"),"%" + description + "%");
	}

}
