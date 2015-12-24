package com.taojia.app.Dao;

import com.taojia.app.Bean.MyPackage;
import com.taojia.app.Bean.User;

public interface MypackageDao {
	public MyPackage[] getMyPackage(String phoneNumber);
	public User[] getMyPackageUser(String mypackageid);
}
