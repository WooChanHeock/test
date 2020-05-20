package kr.inhatc.spring.board.dto;

import java.util.List;

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
	
	//파일 관리를 위한 리스트 추가
	private List<FileDto> fileList;
}
