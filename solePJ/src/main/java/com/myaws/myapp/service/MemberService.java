package com.myaws.myapp.service;

import java.util.ArrayList;

import com.myaws.myapp.domain.MemberVo;

public interface MemberService {

	//���������� ����� �޼ҵ带 �����ϴ� ��
	public int memberInsert(MemberVo mv);
	
	public int memberIdCheck(String memberId); //������� �޼ҵ� ���� 1
	
	public MemberVo memberLoginCheck(String memberId);
		
	public ArrayList<MemberVo> memberSelectAll();
}
