package com.global.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.global.DTO.InstructorDTO;
import com.global.Repo.InstructorRepo;
import com.global.entity.Instructor;
import com.global.exception.NotFoundException;
import com.global.mapper.InstructorMapper;
import com.global.search.InstructorSearch;

@Service
public class InstructorService {
	
	@Autowired
	InstructorRepo instructorRepo;
	@Autowired
	MessageSource messageSource;
	@Autowired
	InstructorMapper mapper;
	@Autowired
	InstructorSearch search;
	
	public void insert(Instructor instructor) {
		instructorRepo.save(instructor);
	}
	
	public Page<Instructor> findAll(int pageNum,int pageSize, String colSort , boolean isASC){
		Sort sort = Sort.by(isASC? Direction.ASC:Direction.DESC ,colSort);
		Pageable page = PageRequest.of(pageNum,pageSize,sort);
		return instructorRepo.findAll(page);
	}
	
	
	public Instructor findById(Long id){
		 Optional<Instructor> entity = instructorRepo.findById(id);
		 if(!entity.isPresent()) {
			 String[] msg = {id.toString()};
			 String message = messageSource.getMessage("id.not.found",msg, LocaleContextHolder.getLocale());
			 throw new NotFoundException(message);
		 }
		 return instructorRepo.findById(id).get();
	}
	
	public void deleteById(Long id) {
		 Optional<Instructor> entity = instructorRepo.findById(id);
		 if(!entity.isPresent()) {
			 String[] msg = {id.toString()};
			 String message = messageSource.getMessage("id.not.found",msg,LocaleContextHolder.getLocale());
			 throw new NotFoundException(message);
		 }
		  instructorRepo.deleteById(id);;
	}
	
	public void update(InstructorDTO DTO) {
		Instructor entity = this.findById(DTO.getId());
		mapper.updateDTO(DTO, entity);
		instructorRepo.save(entity);
	}
	
	
	public List <Instructor> search(String name , String email) {
		Specification<Instructor> spec =Specification.unrestricted();
		if( name!= null && !name.isBlank() ) {
			spec = spec.and(search.searchByName(name));
		}
		
		if( email!= null && !email.isBlank()) {
			spec = spec.and(search.searchByEmail(email));
		}
		
		return instructorRepo.findAll(spec);
		
		
	}
	
	
	
	

}
