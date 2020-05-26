package kr.inhatc.spring.member.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.inhatc.spring.member.dto.FileDto;
import kr.inhatc.spring.member.dto.MemberDto;
import kr.inhatc.spring.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/")
	public String hello() {
		return "index";
	}
	
	@RequestMapping("/member/memberList")
	public String boardList(Model model) {
		List<MemberDto> list = memberService.memberList();
		model.addAttribute("list", list);
		return "member/memberList";
	}
	
	@RequestMapping("/member/memberWrite")
	public String memberWrite() {
		return "/member/memberWrite";
	}
	
	@RequestMapping("/member/memberInsert")
	public String memberInsert(MemberDto member, MultipartHttpServletRequest multipartHttpServletRequest) {
		memberService.memberInsert(member, multipartHttpServletRequest);
		return "redirect:/member/memberList";
	}
	
	@RequestMapping("/member/memberDetail")
	public String memberDetail(@RequestParam int memberIdx, Model model) {
		MemberDto member = memberService.memberDetail(memberIdx);
		model.addAttribute("member", member);
		return "member/memberDetail";
	}
	
	@RequestMapping("/member/memberUpdate")
	public String memberUpdate(MemberDto member) {
		memberService.memberUpdate(member);
		return "redirect:/member/memberList";
	}
	
	@RequestMapping("/member/memberDelete")
	public String memberDelete(@RequestParam("memberIdx") int memberIdx) {
		memberService.memberDelete(memberIdx);
		return "redirect:/member/memberList";
	}
	
	@RequestMapping("/member/downloadMemberFile")
	public void downloadMemberFile(
			@RequestParam("idx") int idx,
			@RequestParam("memberIdx") int memberIdx,
				HttpServletResponse response) throws Exception {

		FileDto memberFile = memberService.selectFileInfo(idx, memberIdx);
		
		if(ObjectUtils.isEmpty(memberFile) == false) {
			String fileName = memberFile.getOriginalFileName();
			byte[] files = FileUtils.readFileToByteArray(new File(memberFile.getStoredFilePath()));
			
			//response 헤더에 설정
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		
	}
	
}
