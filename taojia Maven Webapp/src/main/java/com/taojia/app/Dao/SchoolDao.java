package com.taojia.app.Dao;

import com.taojia.app.Bean.School;

public interface SchoolDao {
	public School[] getSchool(int page,int count);
	public String[] getSchoolEnvironmental(long schoolid);
}
