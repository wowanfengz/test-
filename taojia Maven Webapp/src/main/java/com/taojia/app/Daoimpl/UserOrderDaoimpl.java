package com.taojia.app.Daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.taojia.app.Bean.UserOrder;
import com.taojia.app.Dao.UserOrderDao;
import com.taojia.app.Utils.SessionFactory;
@Service
public class UserOrderDaoimpl implements UserOrderDao{

	public int submitOrder(UserOrder order) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			UserOrderDao userOrderDao =  session.getMapper(UserOrderDao.class);
			int num = userOrderDao.submitOrder(order);
			session.commit();
			return num;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return 0;
	}

	public UserOrder getMyOrderByUserid(long userid) {
		
		return null;
	}
}
