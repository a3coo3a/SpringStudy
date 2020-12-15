package com.team404.command;

import java.sql.Timestamp;

public class FreeBoardVO {
	private int num;
	private String writer;
	private String title;
	private String content;
	private Timestamp regdate;
	private Timestamp modidate;
	
	public FreeBoardVO() {
	}

	public FreeBoardVO(int num, String writer, String title, String content, Timestamp regdate, Timestamp modidate) {
		super();
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.modidate = modidate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public Timestamp getModidate() {
		return modidate;
	}

	public void setModidate(Timestamp modidate) {
		this.modidate = modidate;
	}

	@Override
	public String toString() {
		return "FreeBaordVO [num=" + num + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + ", modidate=" + modidate + "]";
	}

	
	
}
