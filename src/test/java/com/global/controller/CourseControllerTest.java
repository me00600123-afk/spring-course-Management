package com.global.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.Service.CourseService;
import com.global.entity.Course;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc 
public class CourseControllerTest {
	@Autowired
	MockMvc mockMvc;
	@MockitoBean
	CourseService courseService;
	
	@Test
	public void findById() throws Exception {
		mockMvc.perform(get("/course/findById/{id}",1L).contentType("application/json")).andExpect(status().isOk());
		}
	
	@Test
	public void insert() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		Course course = new Course();
		mockMvc.perform(post("/course/insert")
				.contentType("application/json")
				.content(mapper.writeValueAsString(course)))
		.andExpect(status().isExpectationFailed());
		
	}
	
	
	
	
	
	
	

}
