package kr.inhatc.spring.member.dto;

import lombok.Data;

@Data
public class FileDto {

	private int idx;
	private int memberIdx;
	private String originalFileName;
	private String storedFilePath;
	private long fileSize;
	
}
