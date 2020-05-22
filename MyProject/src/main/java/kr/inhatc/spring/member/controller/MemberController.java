package kr.inhatc.spring.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}
