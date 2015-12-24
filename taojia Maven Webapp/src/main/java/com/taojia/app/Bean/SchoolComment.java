package com.taojia.app.Bean;

public class SchoolComment {
	private long schoolcommentid;
	private long schoolid;
	private long userid;
	private long time;
	private String content;
	private String userAvatar;
	private String username;
	
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getSchoolcommentid() {
		return schoolcommentid;
	}
	public void setSchoolcommentid(long schoolcommentid) {
		this.schoolcommentid = schoolcommentid;
	}
	public long getSchoolid() {
		return schoolid;
	}
	public void setSchoolid(long schoolid) {
		this.schoolid = schoolid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time){
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
