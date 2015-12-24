package com.taojia.app.Dao;

import com.taojia.app.Bean.Coupons;

public interface CouponsDao {
	public Coupons[] getCouponsByUserid(long userid,int page,int count);
}
