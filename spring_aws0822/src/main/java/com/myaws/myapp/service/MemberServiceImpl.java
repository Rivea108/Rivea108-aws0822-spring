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
	
	
	
}
