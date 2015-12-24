package com.taojia.app.Bean;

public class Coupons {
	private long couponsid;
	private long userid;
	private double money;
	private String detail;
	private long startTime;
	private long endTime;
	private int isDone;
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
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
}
