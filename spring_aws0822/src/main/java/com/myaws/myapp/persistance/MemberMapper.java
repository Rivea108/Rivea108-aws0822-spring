package com.myaws.myapp.persistance;

import java.util.ArrayList;

import com.myaws.myapp.domain.MemberVo;

//마이바티스용 메소드
public interface MemberMapper {

	public int memberInsert(MemberVo mv);

	public int memberIdCheck(String memberId);

	public MemberVo memberLoginCheck(String memberId);
	
	public ArrayList<MemberVo> memberSelectAll();
}
