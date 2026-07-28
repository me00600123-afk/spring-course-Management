package com.global.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.global.DTO.CourseDTO;
import com.global.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {
	
	 CourseDTO mapToDTO(Course course);
	 
	 @Mapping(target = "id" , ignore = true)
	 void updateDTO(CourseDTO courseDTO,@MappingTarget Course course);
	
	


}
