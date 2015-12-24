package com.taojia.app.Daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.taojia.app.Bean.School;
import com.taojia.app.Dao.SchoolDao;
import com.taojia.app.Utils.SessionFactory;
@Service
public class SchoolDaoimpl implements SchoolDao{

	public School[] getSchool(int page, int count) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			SchoolDao schoolDao = session.getMapper(SchoolDao.class);
			School school_list[] = schoolDao.getSchool(page*count, count);
			session.commit();
			return school_list;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return null;
	}

	public String[] getSchoolEnvironmental(long schoolid) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			SchoolDao schoolDao = session.getMapper(SchoolDao.class);
			String img_list[] = schoolDao.getSchoolEnvironmental(schoolid);
			session.commit();
			return img_list;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return null;
	}

}
