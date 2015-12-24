package com.taojia.app.Dao;

import com.taojia.app.Bean.UserOrder;

public interface UserOrderDao {
	public int submitOrder(UserOrder order);
	public UserOrder getMyOrderByUserid(long userid);
}