package kr.inhatc.spring.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.member.dto.MemberDto;
import kr.inhatc.spring.member.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public List<MemberDto> memberList() {
		return memberMapper.memberList();
	}

}
