package com.ssafy.ssafit.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class RequestObj {

	private User user;
	private MultipartFile file;
	public RequestObj() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequestObj(User user, MultipartFile file) {
		super();
		this.user = user;
		this.file = file;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "RequestObj [user=" + user + ", file=" + file + "]";
	}
	
	
}
