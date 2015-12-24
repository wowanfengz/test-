package com.taojia.app.Dao;

import java.util.ArrayList;

import com.taojia.app.Bean.User;

public interface UserDao {
	public int addUser(User user);
	public User GetUserByPhoneNumber(String phoneNumber);
	public int updateUserLoginStatus(User user);
	public int updatePassword(User user);
	public int updateUser(User user);
}
