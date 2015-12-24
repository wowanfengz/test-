package com.taojia.app.Bean;

public class UserOrder {
	private long userorderid;
	private long userid;
	private long coachid;
	private String coachName;
	private String school;
	private int paytype;
	private double price;
	private long time;
	private int isDone;
	private long couponsid;
	private double couponsPrice;
	
	public double getCouponsPrice() {
		return couponsPrice;
	}
	public void setCouponsPrice(double couponsPrice) {
		this.couponsPrice = couponsPrice;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public long getUserorderid() {
		return userorderid;
	}
	public void setUserorderid(long userorderid) {
		this.userorderid = userorderid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getCoachid() {
		return coachid;
	}
	public void setCoachid(long coachid) {
		this.coachid = coachid;
	}
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getIsDone() {
		return isDone;
	}
	public void setIsDone(int isDone) {
		this.isDone = isDone;
	}
	public long getCouponsid() {
		return couponsid;
	}
	public void setCouponsid(long couponsid) {
		this.couponsid = couponsid;
	}
	
}
