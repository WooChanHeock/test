package kr.inhatc.spring.board.dto;

import lombok.Data;

// Dto = data transfer object db 전달 객체

@Data
public class BoardDto {

	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String createID;
	private String createDatetime;
	
}
