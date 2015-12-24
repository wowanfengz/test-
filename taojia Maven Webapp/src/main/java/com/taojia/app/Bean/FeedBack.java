package com.taojia.app.Bean;

public class FeedBack {
	private long feedBackid;
	private long userid;
	private String content;
	private long time;
	public long getFeedBackid() {
		return feedBackid;
	}
	public void setFeedBackid(long feedBackid) {
		this.feedBackid = feedBackid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}
