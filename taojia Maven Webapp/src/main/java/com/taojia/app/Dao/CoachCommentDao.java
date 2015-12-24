package com.taojia.app.Dao;

import com.taojia.app.Bean.CoachComment;

public interface CoachCommentDao {
	public CoachComment[] getCoachComment(long coachid,int comm_page,int comm_count);
}
