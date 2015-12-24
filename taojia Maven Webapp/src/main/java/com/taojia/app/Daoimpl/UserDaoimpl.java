package com.taojia.app.Daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.taojia.app.Bean.User;
import com.taojia.app.Dao.UserDao;
import com.taojia.app.Utils.SessionFactory;
@Service
public class UserDaoimpl implements UserDao{

	public int addUser(User user) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			UserDao userDao = session.getMapper(UserDao.class);
			int num = userDao.addUser(user);
			session.commit();
			return num;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return 0;
	}

	public User GetUserByPhoneNumber(String username) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			UserDao userDao = session.getMapper(UserDao.class);
			User user = userDao.GetUserByPhoneNumber(username);
			session.commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return null;
	}

	public int updateUserLoginStatus(User user) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			UserDao userDao = session.getMapper(UserDao.class);
			int num = userDao.updateUserLoginStatus(user);
			session.commit();
			return num;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return 0;
	}

	public int updatePassword(User user) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			UserDao userDao = session.getMapper(UserDao.class);
			int num = userDao.updatePassword(user);
			session.commit();
			return num;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return 0;
	}

	public int updateUser(User user) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			UserDao userDao = session.getMapper(UserDao.class);
			int num = userDao.updateUser(user);
			session.commit();
			return num;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return 0;
	}

}
