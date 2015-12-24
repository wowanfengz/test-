package com.taojia.app.Daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.taojia.app.Bean.CoachComment;
import com.taojia.app.Dao.CoachCommentDao;
import com.taojia.app.Utils.SessionFactory;
@Service
public class CoachCommentDaoimpl implements CoachCommentDao{

	public CoachComment[] getCoachComment(long coachid, int comm_page,
			int comm_count) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			CoachCommentDao coachCommentDao = session.getMapper(CoachCommentDao.class);
			CoachComment[] coachComments = coachCommentDao.getCoachComment(coachid, comm_page*comm_count, comm_count);
			session.commit();
			return coachComments;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return null;
	}

}
