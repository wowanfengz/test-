package com.taojia.app.Dao;

import com.taojia.app.Bean.Camp;
import com.taojia.app.Bean.Coach;
import com.taojia.app.Bean.User;

public interface CampDao {
	public Camp[] getCamp();
	public User[] getCampUser(long campid);
	public Coach[] getCampCoach(long campid);
}
