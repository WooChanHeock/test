package kr.inhatc.spring.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.inhatc.spring.board.dto.BoardDto;
import kr.inhatc.spring.board.dto.FileDto;
import kr.inhatc.spring.member.dto.MemberDto;

@Mapper
public interface MemberMapper {

	// 메소드의 이름과 쿼리의 이름을 동일하게 처리해야 한다.
	List<MemberDto> memberList();

	void memberInsert(MemberDto member);

	MemberDto memberDetail(int memberIdx);

	void memberUpdate(MemberDto member);

	void memberDelete(int memberIdx);

	void memberFileInsert(List<kr.inhatc.spring.member.dto.FileDto> list);

	List<kr.inhatc.spring.member.dto.FileDto> selectMemberFileList(int memberIdx);

	kr.inhatc.spring.member.dto.FileDto selectFileInfo(int idx, int memberIdx);


}
