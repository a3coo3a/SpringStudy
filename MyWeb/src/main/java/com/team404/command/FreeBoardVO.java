package com.team404.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FreeBoardVO {
	private int bno;
	private String writer;
	private String title;
	private String content;
	private Timestamp regdate;
	private Timestamp updatedate;	
}
