package com.global.search;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.global.entity.Instructor;

@Component
public class Search{
	
	public static Specification<Instructor> searchByName(String name){
		return (root, query, cb) ->
        cb.like(
                root.get("fullName"),
                "%" + name+ "%"
        );
		}
	
	
	public static Specification<Instructor> searchByEmail(String email){
		
		return (root,query,cb) -> 
			cb.like(root.get("email"),"%" + email + "%");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	





