package com.global.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.DTO.InstructorDTO;
import com.global.Service.InstructorService;
import com.global.entity.Instructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@Validated
@RequestMapping("/instructor")
@RestController
public class InstructorController {
	@Autowired
	InstructorService instructorService;
	
	
	
	@Operation(summary = "insert instructor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description= "instructor insert successfully" , content = @Content( mediaType = "application/json",schema = @Schema(implementation =Instructor.class ))),
			@ApiResponse(responseCode="404" , description = "violation validation", content = @Content)
			
	})
	@PostMapping("/insert")
	public void insert(@Valid @RequestBody Instructor instructor) {
		instructorService.insert(instructor);
	}
	@Operation(summary = "find all instructor")
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll(@RequestParam int pageNum,@RequestParam int pageSize,@RequestParam String colSort ,@RequestParam boolean isASC){
		return ResponseEntity.ok(instructorService.findAll(pageNum, pageSize, colSort, isASC));
	}

	@Operation(summary = "find by id, id must be valid")
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return  ResponseEntity.ok(instructorService.findById(id));
	}

	@Operation(summary = "delete by id, id must be valid")
	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable Long id) {
		instructorService.deleteById(id);
	}

	@Operation(summary = "update instructor")
	@PutMapping("/update")
	public void update(@Valid @RequestBody InstructorDTO DTO) {
		instructorService.update(DTO);
	}

	@Operation(summary = "searching by name and email")
	@GetMapping("/search")
	public List <Instructor> search(@RequestParam String name ,@RequestParam String email) {
		return instructorService.search(name, email);	
	}
	
	
	
	
	
	

}
