package com.taojia.app.Bean;

import java.util.List;

public class MyPackage {
	private long mypackageid;
	private long userid;
	private String name;
	private int member;
	private User[] friends;
	public User[] getFriends() {
		return friends;
	}
	public void setFriends(User[] friends) {
		this.friends = friends;
	}
	public long getMypackageid() {
		return mypackageid;
	}
	public void setMypackageid(long mypackageid) {
		this.mypackageid = mypackageid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
	}
	
}
