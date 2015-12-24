package com.taojia.app.Bean;

public class CoachComment {
	private long coachcommentid;
	private long userid;
	private String content;
	private long time;
	private long coachid;
	private String userAvatar;
	private String username;	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public long getCoachcommentid() {
		return coachcommentid;
	}
	public void setCoachcommentid(long coachcommentid) {
		this.coachcommentid = coachcommentid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getCoachid() {
		return coachid;
	}
	public void setCoachid(long coachid) {
		this.coachid = coachid;
	}
	
}
