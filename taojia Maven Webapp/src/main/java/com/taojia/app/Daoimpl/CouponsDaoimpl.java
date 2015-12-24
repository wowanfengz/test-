package com.taojia.app.Daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.taojia.app.Bean.Coupons;
import com.taojia.app.Dao.CouponsDao;
import com.taojia.app.Utils.SessionFactory;
@Service
public class CouponsDaoimpl implements CouponsDao{

	public Coupons[] getCouponsByUserid(long userid,int page,int count) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			CouponsDao couponsDao = session.getMapper(CouponsDao.class);
			Coupons[] coach_list = couponsDao.getCouponsByUserid(userid,page*count,count);
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
