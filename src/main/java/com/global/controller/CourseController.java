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

import com.global.DTO.CourseDTO;
import com.global.Service.CourseService;
import com.global.entity.Course;
import com.global.page.PageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/course")
@Validated
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@Operation(summary = "insert course")
	@ApiResponses(value = {@ApiResponse(responseCode = "200" , description = "success, course insert" , content = {
			@Content(mediaType = "application/json" , schema = @Schema(implementation = Course.class))	
	}),
			@ApiResponse(responseCode = "404" , description = "violation validation",content = @Content)
	})
	@PostMapping("/insert")
	public Course insert(@Valid @RequestBody Course course) {

		return courseService.insert(course);
	}
	
	
	@ApiResponses(value = {@ApiResponse(responseCode = "200" , description = "success, findll" , content = {
			@Content(mediaType = "application/json" , schema = @Schema(implementation = Course.class))	
	}),
			@ApiResponse(responseCode = "404" , description = "invalid resource",content = @Content)
	})
	@GetMapping("/findAll")
	public ResponseEntity<PageResponse<Course>> findAll(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String colName,
			@RequestParam boolean isASC) {
		return ResponseEntity.ok(courseService.findAll(pageNum, pageSize, colName, isASC));
	}
	
	@ApiResponses(value = {@ApiResponse(responseCode = "200" , description = "success, find by id" , content = {
			@Content(mediaType = "application/json" , schema = @Schema(implementation = Course.class))	
	}),
			@ApiResponse(responseCode = "404" , description = "id not found",content = @Content)
	})
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok(courseService.findById(id));
	}
	
	@Operation(summary = "update course")
	@PutMapping("/update")
	public void update(@RequestBody CourseDTO course) {
		courseService.update(course);
	}
	
	@Operation(summary = "delete by id")
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		courseService.delete(id);

	}
	
	@Operation(summary = "search by title and level and status and minimum price and maximum price ")
	@GetMapping("/search")
	public List<Course> search(@RequestParam String title ,@RequestParam String level,@RequestParam String status,@RequestParam Long minPrice ,@RequestParam Long maxPrice,@RequestParam String description) {
		return courseService.search(title,level,status,minPrice,maxPrice,description);
	}

}
