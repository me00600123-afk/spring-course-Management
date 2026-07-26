package com.global.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.global.entity.Instructor;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Long> , JpaSpecificationExecutor<Instructor>{
	
	public Page<Instructor> findAll(Pageable page);

}
