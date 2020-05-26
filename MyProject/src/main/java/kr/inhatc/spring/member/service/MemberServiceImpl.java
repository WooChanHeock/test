package kr.inhatc.spring.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.inhatc.spring.member.dto.FileDto;
import kr.inhatc.spring.member.dto.MemberDto;
import kr.inhatc.spring.member.mapper.MemberMapper;
import kr.inhatc.spring.utils.FileUtils2;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private FileUtils2 fileUtils;
	
	@Override
	public List<MemberDto> memberList() {
		return memberMapper.memberList();
	}

	@Override
	public void memberInsert(MemberDto member, MultipartHttpServletRequest multipartHttpServletRequest) {
		memberMapper.memberInsert(member);
		
		//1.파일 저장
		List<FileDto> list = fileUtils.parseFileInfo(member.getMemberIdx(), multipartHttpServletRequest); 
			
		//2.디비 저장
		if(CollectionUtils.isEmpty(list) == false) {
		memberMapper.memberFileInsert(list);
		}
	}

	@Override
	public MemberDto memberDetail(int memberIdx) {
		MemberDto member = memberMapper.memberDetail(memberIdx);
		
		// 파일 정보
		List<FileDto> fileList = memberMapper.selectMemberFileList(memberIdx);
		member.setFileList(fileList);
		
		return member;
	}

	@Override
	public void memberUpdate(MemberDto member) {
		memberMapper.memberUpdate(member);
		
	}

	@Override
	public void memberDelete(int memberIdx) {
		memberMapper.memberDelete(memberIdx);
		
	}

	@Override
	public FileDto selectFileInfo(int idx, int memberIdx) {
		FileDto memberFile = memberMapper.selectFileInfo(idx, memberIdx);
		return memberFile;
	}

}
