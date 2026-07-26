package com.global.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.global.DTO.InstructorDTO;
import com.global.entity.Instructor;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
	
	public InstructorDTO mapToEntity(Instructor entity);
	
	
	@Mapping(target = "id" , ignore = true)
	void updateDTO(InstructorDTO instructorDTO ,@MappingTarget Instructor instructor);

}
