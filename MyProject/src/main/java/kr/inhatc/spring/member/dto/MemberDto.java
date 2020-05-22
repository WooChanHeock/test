package kr.inhatc.spring.member.dto;

import java.util.List;

import lombok.Data;

// Dto = data transfer object db 전달 객체

@Data
public class MemberDto {

	private String email;
	private String enabled;
	private String joinDate;
	private String memberID;
	private String name;
	private String pw;
	private String role;
	private int memberIdx;
	
	
}
