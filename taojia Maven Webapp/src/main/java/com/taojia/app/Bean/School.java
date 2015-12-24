package com.taojia.app.Bean;

public class School {
	private long schoolid;
	private String name;
	private double longitude;
	private double latitude;
	private long registerNumber;
	private long passNumber;
	private long areaid;
	private String country;
	private String province;
	private String city;
	private String area;
	private String detail;
	private double star;
	private double serviceStar;
	private double teachStar;
	private double teachSpeed;
	private String environmental_img_list[];
	private String QRcode;
	private String address;	
	private String avatar;
	private SchoolComment[] schoolComments;
	public SchoolComment[] getSchoolComments() {
		return schoolComments;
	}
	public void setSchoolComments(SchoolComment[] schoolComments) {
		this.schoolComments = schoolComments;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	public String getQRcode() {
		return QRcode;
	}
	public void setQRcode(String qRcode) {
		QRcode = qRcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getSchoolid() {
		return schoolid;
	}
	public void setSchoolid(long schoolid) {
		this.schoolid = schoolid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public long getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(long registerNumber) {
		this.registerNumber = registerNumber;
	}
	public long getPassNumber() {
		return passNumber;
	}
	public void setPassNumber(long passNumber) {
		this.passNumber = passNumber;
	}
	public long getAreaid() {
		return areaid;
	}
	public void setAreaid(long areaid) {
		this.areaid = areaid;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
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
	public String[] getEnvironmental_img_list() {
		return environmental_img_list;
	}
	public void setEnvironmental_img_list(String[] environmental_img_list) {
		this.environmental_img_list = environmental_img_list;
	}
	
}
