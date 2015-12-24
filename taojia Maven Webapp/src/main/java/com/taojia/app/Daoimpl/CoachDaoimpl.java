package com.taojia.app.Daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.taojia.app.Bean.Coach;
import com.taojia.app.Dao.CoachDao;
import com.taojia.app.Utils.SessionFactory;
@Service
public class CoachDaoimpl implements CoachDao{

	public Coach[] getCoachBySchoolid(int schoolid,int page, int count) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			CoachDao coachDao = session.getMapper(CoachDao.class);
			Coach coach_list[] = coachDao.getCoachBySchoolid(schoolid,page*count,count);
			session.commit();
			return coach_list;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return null;
	}

	public Coach[] getCoach(int page, int count) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			CoachDao coachDao = session.getMapper(CoachDao.class);
			Coach coach_list[] = coachDao.getCoach(page*count,count);
			session.commit();
			return coach_list;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return null;
	}
	
}
