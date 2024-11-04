package com.myaws.myapp.controller;

import java.lang.ProcessBuilder.Redirect;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws.myapp.domain.MemberVo;
import com.myaws.myapp.service.MemberService;

import netscape.javascript.JSObject;

@Controller //객체생성, @어노테이션
@RequestMapping(value="/member/")
public class MemberController { //객체생성
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	 //@Autowired 
	 //private Test tt;
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 
	
	@RequestMapping(value="memberJoin.aws", method=RequestMethod.GET)
	public String memberJoin() {
		logger.info("memberJoin들어옴");
		
		
		
		//logger.info("tt값은? :" + tt.test());
		
		return "WEB-INF/member/memberJoin";
	}	
	
	//시작
	
	  @RequestMapping(value="memberJoinAction.aws", method=RequestMethod.POST)
	  public String memberJoinAction(MemberVo mv) {
	  logger.info("memberJoinAction들어옴");
		
		  logger.info("bCryptPasswordEncoder들어옴" + bCryptPasswordEncoder);
		  
		  String memberpwd_enc = bCryptPasswordEncoder.encode(mv.getMemberpwd());
		 
		mv.setMemberpwd(memberpwd_enc);	
	  
	  
	  int value = memberService.memberInsert(mv);
	  logger.info("value : "+value); //value안먹음
	  
	  String path=""; if(value==1) { path = "redirect:/"; }else if(value==0) {
	  path= "redirect:/member/memberJoin.aws"; }
	  
	  
	  return path; }
	 
	//종료
	
	
	
	@RequestMapping(value="memberLogin.aws", method=RequestMethod.GET)
	public String memberLogin() {
		
		return "WEB-INF/member/memberLogin";
	}
	
	@RequestMapping(value="memberLoginAction.aws", method=RequestMethod.POST)
	public String memberLoginAction(
			@RequestParam("memberid") String memberId,
			@RequestParam("memberpwd") String memberpwd,
			RedirectAttributes rttr
			){
		
		//이곳의 @RequestParam("memberid") String memberId를 해석하자면 memberid를 가져와서 String형태의 memberId로 변경하겠다는 것 
		//그리고 87줄의 memberId는 이곳에서 소문자가 대문자로 변경했기에 대문자로 사용
		
		//스프링의 @RequestParam은 이클립스의 String memberId = request.getParameter("memberId");을 대체가능
		//멤버로그인의 memberid를 멤버컨트롤러에서 memberId로 변경했기에 받는 값이 달라져서 에러가 났음
		
		MemberVo mv = memberService.memberLoginCheck(memberId);
		//저장된 비밀번호를 가져온다
		//System.out.println("mv"+mv); 디버깅코드
		//System.out.println(memberId); 디버깅코드
		
		String path = "";
		if(mv != null) { //객체값이 없으면 
		String reservedPwd = mv.getMemberpwd();
		
		if(bCryptPasswordEncoder.matches(memberpwd, reservedPwd)) {
			//System.out.println("비밀번호 일치");
			rttr.addAttribute("midx",mv.getMidx());
			rttr.addAttribute("memberId", mv.getMemberid());
			rttr.addAttribute("memberName", mv.getMembername());
			
		path="redirect:/";	
		}else { 
			/*
			 * rttr.addAttribute("midx", ""); 
			 * rttr.addAttribute("memberId", "");
			 * rttr.addAttribute("memberName", "");
			 */
			rttr.addFlashAttribute("msg", "아이디/비밀번호를 확인해주세요."); //한번만 사용가능 : 한번사용하면 세션에서 지워버림
			//f5를 반복해서 5번 눌러도 1번밖에 뜨지않는다는거임
			path="redirect:/member/memberLogin.aws";
		}
	}else {
		/*
		 * rttr.addAttribute("midx", ""); 
		 * rttr.addAttribute("memberId", "");
		 * rttr.addAttribute("memberName", "");
		 */
		rttr.addFlashAttribute("msg", "해당하는 아이디가 없습니다."); //한번만 사용가능 
		path="redirect:/member/memberLogin.aws";
	}
	
		//회원정보를 session에 담는다
		//로그인이 안되면 다시 로그인 페이지로 가고
		//로그인이 되면 메인으로 가라 
		
		
		return path;
	}
	
	@ResponseBody
	@RequestMapping(value="memberIdCheck.aws", method=RequestMethod.POST)
	public JSONObject memberIdCheck(@RequestParam("memberId")String memberId) {
		
	
	int cnt = memberService.memberIdCheck(memberId);//메소드 설정
	
	
	JSONObject obj = new JSONObject();
	obj.put("cnt", cnt);
		
		return obj;
	}

	
	
	
	
	
}
