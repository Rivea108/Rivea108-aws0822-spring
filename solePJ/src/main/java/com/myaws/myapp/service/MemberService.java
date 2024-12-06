package com.myaws.myapp.service;

import java.util.ArrayList;

import com.myaws.myapp.domain.MemberVo;

public interface MemberService {

	//스프링에서 사용할 메소드를 선언하는 곳
	public int memberInsert(MemberVo mv);
	
	public int memberIdCheck(String memberId); //멤버조인 메소드 생성 1
	
	public MemberVo memberLoginCheck(String memberId);
		
	public ArrayList<MemberVo> memberSelectAll();
}
