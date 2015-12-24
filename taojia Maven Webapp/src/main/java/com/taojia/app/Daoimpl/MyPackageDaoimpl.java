package com.taojia.app.Daoimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.taojia.app.Bean.MyPackage;
import com.taojia.app.Bean.School;
import com.taojia.app.Bean.User;
import com.taojia.app.Dao.MypackageDao;
import com.taojia.app.Dao.SchoolDao;
import com.taojia.app.Utils.SessionFactory;
@Service
public class MyPackageDaoimpl implements MypackageDao{

	public MyPackage[] getMyPackage(String phoneNumber) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			MypackageDao myPackageDao = session.getMapper(MypackageDao.class);
			MyPackage myPackage_list[] =myPackageDao.getMyPackage(phoneNumber);
			session.commit();
			return myPackage_list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	public User[] getMyPackageUser(String mypackageid) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			MypackageDao myPackageDao = session.getMapper(MypackageDao.class);
			User myPackageUser_list[] =myPackageDao.getMyPackageUser(mypackageid);
			session.commit();
			return myPackageUser_list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
}
