package com.myaws.myapp.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.domain.MemberVo;
import com.myaws.myapp.persistance.MemberMapper;
	
@Service //예전에는 컴포넌트나 빈을 사용했음
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
	public int memberIdCheck(String memberId) { //메소드 생성 2번
			int value = mm.memberIdCheck(memberId);
		return value;
	}
	
	@Override
	public MemberVo memberLoginCheck(String memberId) { 
		   MemberVo mv = mm.memberLoginCheck(memberId);
		   System.out.println("mv");
		return mv;
	}
	
	
}
