package com.taojia.app.Dao;

import com.taojia.app.Bean.Coach;

public interface CoachDao {
	public Coach[] getCoachBySchoolid(int schoolid,int page,int count);
	public Coach[] getCoach(int page,int count);
}
