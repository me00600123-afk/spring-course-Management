package com.global.page;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageResponse<T> {
	
	private List<T> content;
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private Long totalElements;
	private boolean first;
	private boolean last;
	
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public boolean getFirst() {
		return first;
	}
	public void setFirst(boolean first) {
		this.first = first;
	}
	public boolean getLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	
	public static <T> PageResponse<T> from(Page<T> page){
		PageResponse<T> response = new PageResponse<T>();
		response.setContent(page.getContent());
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalPages(page.getTotalPages());
		response.setTotalElements(page.getTotalElements());
		response.setFirst(page.isFirst());
		response.setLast(page.isLast());
		return response;
	}

	
	
}
