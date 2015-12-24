package com.taojia.app.Daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.taojia.app.Bean.Coupons;
import com.taojia.app.Bean.FeedBack;
import com.taojia.app.Dao.CouponsDao;
import com.taojia.app.Dao.FeedBackDao;
import com.taojia.app.Utils.SessionFactory;
@Service
public class FeedBackDaoimpl implements FeedBackDao{

	public int insertFeedBack(FeedBack feedBack) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			FeedBackDao feedBackDao = session.getMapper(FeedBackDao.class);
			int result = feedBackDao.insertFeedBack(feedBack);
			session.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return 0;
	}
	
}
