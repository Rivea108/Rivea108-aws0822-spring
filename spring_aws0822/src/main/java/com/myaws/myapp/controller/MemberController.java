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

@Controller //��ü����, @������̼�
@RequestMapping(value="/member/")
public class MemberController { //��ü����
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	 //@Autowired 
	 //private Test tt;
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 
	
	@RequestMapping(value="memberJoin.aws", method=RequestMethod.GET)
	public String memberJoin() {
		logger.info("memberJoin����");
		
		
		
		//logger.info("tt����? :" + tt.test());
		
		return "WEB-INF/member/memberJoin";
	}	
	
	//����
	
	  @RequestMapping(value="memberJoinAction.aws", method=RequestMethod.POST)
	  public String memberJoinAction(MemberVo mv) {
	  logger.info("memberJoinAction����");
		
		  logger.info("bCryptPasswordEncoder����" + bCryptPasswordEncoder);
		  
		  String memberpwd_enc = bCryptPasswordEncoder.encode(mv.getMemberpwd());
		 
		mv.setMemberpwd(memberpwd_enc);	
	  
	  
	  int value = memberService.memberInsert(mv);
	  logger.info("value : "+value); //value�ȸ���
	  
	  String path=""; if(value==1) { path = "redirect:/"; }else if(value==0) {
	  path= "redirect:/member/memberJoin.aws"; }
	  
	  
	  return path; }
	 
	//����
	
	
	
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
		
		//�̰��� @RequestParam("memberid") String memberId�� �ؼ����ڸ� memberid�� �����ͼ� String������ memberId�� �����ϰڴٴ� �� 
		//�׸��� 87���� memberId�� �̰����� �ҹ��ڰ� �빮�ڷ� �����߱⿡ �빮�ڷ� ���
		
		//�������� @RequestParam�� ��Ŭ������ String memberId = request.getParameter("memberId");�� ��ü����
		//����α����� memberid�� �����Ʈ�ѷ����� memberId�� �����߱⿡ �޴� ���� �޶����� ������ ����
		
		MemberVo mv = memberService.memberLoginCheck(memberId);
		//����� ��й�ȣ�� �����´�
		//System.out.println("mv"+mv); ������ڵ�
		//System.out.println(memberId); ������ڵ�
		
		String path = "";
		if(mv != null) { //��ü���� ������ 
		String reservedPwd = mv.getMemberpwd();
		
		if(bCryptPasswordEncoder.matches(memberpwd, reservedPwd)) {
			//System.out.println("��й�ȣ ��ġ");
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
			rttr.addFlashAttribute("msg", "���̵�/��й�ȣ�� Ȯ�����ּ���."); //�ѹ��� ��밡�� : �ѹ�����ϸ� ���ǿ��� ��������
			//f5�� �ݺ��ؼ� 5�� ������ 1���ۿ� �����ʴ´ٴ°���
			path="redirect:/member/memberLogin.aws";
		}
	}else {
		/*
		 * rttr.addAttribute("midx", ""); 
		 * rttr.addAttribute("memberId", "");
		 * rttr.addAttribute("memberName", "");
		 */
		rttr.addFlashAttribute("msg", "�ش��ϴ� ���̵� �����ϴ�."); //�ѹ��� ��밡�� 
		path="redirect:/member/memberLogin.aws";
	}
	
		//ȸ�������� session�� ��´�
		//�α����� �ȵǸ� �ٽ� �α��� �������� ����
		//�α����� �Ǹ� �������� ���� 
		
		
		return path;
	}
	
	@ResponseBody
	@RequestMapping(value="memberIdCheck.aws", method=RequestMethod.POST)
	public JSONObject memberIdCheck(@RequestParam("memberId")String memberId) {
		
	
	int cnt = memberService.memberIdCheck(memberId);//�޼ҵ� ����
	
	
	JSONObject obj = new JSONObject();
	obj.put("cnt", cnt);
		
		return obj;
	}

	
	
	
	
	
}
