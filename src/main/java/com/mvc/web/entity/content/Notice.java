package com.mvc.web.entity.content;

import java.util.Date;

public class Notice {
	String board;
	int id;
	String title;
	String writeid;
	String content;
	Date regdate;
	int hit;
	String boardid;
	/*
	 * public Notice(String userID,String title,String content) {
	 * 
	 * }
	 */
	
	public Notice(String title,String content,String writeid) {
		this.title = title;
		this.writeid = writeid;
		this.content = content;
	}
	
	
	
	public Notice(String board, int id, String title, String writeid, String content, Date regdate, int hit,
			String boardid) {
		
		this.board = board;
		this.id = id;
		this.title = title;
		this.writeid = writeid;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.boardid = boardid;
	}



	public Notice(int id, String title, String writeid, String content, Date regdate, int hit,String board) {
		this.id = id;
		this.title = title;
		this.writeid = writeid;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.board=board;
	}
	
	public Notice(int id, String title, String writeid, String content, Date regdate, int hit) {
		this.id = id;
		this.title = title;
		this.writeid = writeid;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		
	}
	


	
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writeid=" + writeid + ", content=" + content + ", regdate="
				+ regdate + ", hit=" + hit + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriteid() {
		return writeid;
	}
	public void setWriteid(String writeid) {
		this.writeid = writeid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
