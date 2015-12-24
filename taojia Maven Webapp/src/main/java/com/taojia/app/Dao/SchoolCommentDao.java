package com.taojia.app.Dao;

import com.taojia.app.Bean.SchoolComment;

public interface SchoolCommentDao {
	public SchoolComment[] getSchoolCommentBySchoolid(long schoolid,int page,int count);
}
