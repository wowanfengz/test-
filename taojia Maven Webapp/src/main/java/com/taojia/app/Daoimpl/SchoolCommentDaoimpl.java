package com.taojia.app.Daoimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.taojia.app.Bean.SchoolComment;
import com.taojia.app.Dao.SchoolCommentDao;
import com.taojia.app.Utils.SessionFactory;
@Service
public class SchoolCommentDaoimpl implements SchoolCommentDao{

	public SchoolComment[] getSchoolCommentBySchoolid(long schoolid,int page,int count) {
		SqlSession session = SessionFactory.getSessionFactory().openSession();
		try {
			SchoolCommentDao schoolCommentDao = session.getMapper(SchoolCommentDao.class);
			SchoolComment schoolComment_list[] = schoolCommentDao.getSchoolCommentBySchoolid(schoolid, page*count, count);
			session.commit();
			return schoolComment_list;
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			session.close();
		}
		return null;
	}

}
