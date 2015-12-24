package com.taojia.app.Bean;

public class Coach {
	private long coachid;
	private String name;
	private double star;
	private double serviceStar;
	private double teachStar;
	private double teachSpeed;
	private String detail;
	private int registerNumber;
	private int passNumber;
	private String country;
	private String province;
	private String city;
	private String area;
	private String school;
	private int sex;
	private double fee;
	private String avatar;
	private int schoolid;
	private CoachComment[] coachComments;
	
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public CoachComment[] getCoachComments() {
		return coachComments;
	}
	public void setCoachComments(CoachComment[] coachComments) {
		this.coachComments = coachComments;
	}
	public int getSchoolid() {
		return schoolid;
	}
	public void setSchoolid(int schoolid) {
		this.schoolid = schoolid;
	}
	
	public long getCoachid() {
		return coachid;
	}
	public void setCoachid(long coachid) {
		this.coachid = coachid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getStar() {
		return star;
	}
	public void setStar(double star) {
		this.star = star;
	}
	public double getServiceStar() {
		return serviceStar;
	}
	public void setServiceStar(double serviceStar) {
		this.serviceStar = serviceStar;
	}
	public double getTeachStar() {
		return teachStar;
	}
	public void setTeachStar(double teachStar) {
		this.teachStar = teachStar;
	}
	public double getTeachSpeed() {
		return teachSpeed;
	}
	public void setTeachSpeed(double teachSpeed) {
		this.teachSpeed = teachSpeed;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}
	public int getPassNumber() {
		return passNumber;
	}
	public void setPassNumber(int passNumber) {
		this.passNumber = passNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
