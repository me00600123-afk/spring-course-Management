package com.global.DTO;

import com.global.customenum.Level;
import com.global.customenum.Status;
import com.global.entity.Instructor;


public class CourseDTO {
	private Long id;
	
	private String title;
	
	private String description;
	
	private Long price;
	
	private Long durationInHours;
	
	private Level  level;
	
	private Long maxStudents;
	
	private  Status status;
	
	private Long instructorId;
	

	public Long getinstructorId() {
		return instructorId;
	}

	public void setinstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getDurationInHours() {
		return durationInHours;
	}

	public void setDurationInHours(Long durationInHours) {
		this.durationInHours = durationInHours;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Long getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(Long maxStudents) {
		this.maxStudents = maxStudents;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
	
	
}
