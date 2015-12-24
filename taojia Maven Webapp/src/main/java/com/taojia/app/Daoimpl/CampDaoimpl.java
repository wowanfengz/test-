package com.taojia.app.Daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.taojia.app.Bean.Camp;
import com.taojia.app.Bean.Coach;
import com.taojia.app.Bean.CoachComment;
import com.taojia.app.Bean.User;
import com.taojia.app.Dao.CampDao;
import com.taojia.app.Dao.CoachCommentDao;
import com.taojia.app.Utils.SessionFactory;
@Service
public class CampDaoimpl implements CampDao{

	@Override
	public Camp[] getCamp() {
		Camp[] camps= null;
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			CampDao campDao = session.getMapper(CampDao.class);
			camps = campDao.getCamp();
			session.commit();
			return camps;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public User[] getCampUser(long campid) {
		User[] users= null;
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			CampDao campDao = session.getMapper(CampDao.class);
			users = campDao.getCampUser(campid);
			session.commit();
			return users;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public Coach[] getCampCoach(long campid) {
		Coach[] coachs= null;
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			CampDao campDao = session.getMapper(CampDao.class);
			coachs = campDao.getCampCoach(campid);
			session.commit();
			return coachs;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return null;
	}

}
