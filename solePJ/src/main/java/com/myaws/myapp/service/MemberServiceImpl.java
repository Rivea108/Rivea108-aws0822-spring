package com.myaws.myapp.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.domain.MemberVo;
import com.myaws.myapp.persistance.MemberMapper;
	
@Service //�������� ������Ʈ�� ���� �������
public class MemberServiceImpl implements MemberService {	
	
	private MemberMapper mm;
	
	
	
	@Autowired
	public MemberServiceImpl(SqlSession sqlSession) {
	this.mm = sqlSession.getMapper(MemberMapper.class);
	}
	
	

	@Override
	public int memberInsert(MemberVo mv) {
		int value = mm.memberInsert(mv);
		return value;
	}



	@Override
	public int memberIdCheck(String memberId) { //�޼ҵ� ���� 2��
			int value = mm.memberIdCheck(memberId);
		return value;
	}
	
	@Override
	public MemberVo memberLoginCheck(String memberId) { 
		   MemberVo mv = mm.memberLoginCheck(memberId);
		   System.out.println("mv");
		return mv;
	}



	@Override
	   public ArrayList<MemberVo> memberSelectAll() {
	      
	      ArrayList<MemberVo> alist = mm.memberSelectAll();
	      
	      return alist;
	   }

	
}