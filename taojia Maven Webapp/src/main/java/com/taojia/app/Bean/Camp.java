package com.taojia.app.Bean;

public class Camp {
	private long campid;
	private String name;
	private int people_sum;
	private long start_time;
	private long end_time;
	private String camp_img;
	private String detail;
	private int now_people_sum;
	private int coach_count;
	private User[] users;
	private Coach[] coachs;
	public int getNow_people_sum() {
		return now_people_sum;
	}
	public void setNow_people_sum(int now_people_sum) {
		this.now_people_sum = now_people_sum;
	}
	public int getCoach_count() {
		return coach_count;
	}
	public void setCoach_count(int coach_count) {
		this.coach_count = coach_count;
	}
	public User[] getUsers() {
		return users;
	}
	public void setUsers(User[] users) {
		this.users = users;
	}
	public Coach[] getCoachs() {
		return coachs;
	}
	public void setCoachs(Coach[] coachs) {
		this.coachs = coachs;
	}
	public long getCampid() {
		return campid;
	}
	public void setCampid(long campid) {
		this.campid = campid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPeople_sum() {
		return people_sum;
	}
	public void setPeople_sum(int people_sum) {
		this.people_sum = people_sum;
	}
	public long getStart_time() {
		return start_time;
	}
	public void setStart_time(long start_time) {
		this.start_time = start_time;
	}
	public long getEnd_time() {
		return end_time;
	}
	public void setEnd_time(long end_time) {
		this.end_time = end_time;
	}
	public String getCamp_img() {
		return camp_img;
	}
	public void setCamp_img(String camp_img) {
		this.camp_img = camp_img;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
