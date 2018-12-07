package com.homeworkNotice.dto;

public class UserDto {
	private String name;
	private String stuId;
	private int semester;
	private int flag;
	private String pw;
	private String email;
	private String token;
	private String useCookie;
	private String sessionkey;
	private String sessionlimit;
	

	public String getUseCookie() {
		return useCookie;
	}
	public void setUseCookie(String useCookie) {
		this.useCookie = useCookie;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public String getSessionKey() {
		return sessionkey;
	}
	public void setSessionKey(String sessionkey) {
		this.sessionkey = sessionkey;
	}
	
	public String getSessionLimit() {
		return sessionlimit;
	}
	public void setSessionLimit(String sessionlimit) {
		this.sessionlimit = sessionlimit;
	}
	
}
