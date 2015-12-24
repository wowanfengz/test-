package com.taojia.app.Bean;


public class User {
	private int userid;
	private String username;
	private String password;
	private String phoneNumber;
	//1.男    2. 女     3.其他
	private int sex;
	private String email;
	private long loginTime;
	private long registerTime;
	private long birthday;	
	private String token;
	private String avatar;	
	private int progress;	
	private Coupons[] coupons_list;
	private String rongYunToken;
	
	public String getRongYunToken() {
		return rongYunToken;
	}
	public void setRongYunToken(String rongYunToken) {
		this.rongYunToken = rongYunToken;
	}
	public Coupons[] getCoupons_list() {
		return coupons_list;
	}
	public void setCoupons_list(Coupons[] coupons_list) {
		this.coupons_list = coupons_list;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public long getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(long l) {
		this.registerTime = l;
	}
	public long getBirthday() {
		return birthday;
	}
	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}
}
