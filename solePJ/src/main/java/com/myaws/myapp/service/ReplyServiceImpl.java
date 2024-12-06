package com.myaws.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.ReplyVo;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.persistance.BoardMapper;
import com.myaws.myapp.persistance.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{

	private ReplyMapper rm;
	
	@Autowired
	public void replyServiceImpl(SqlSession sqlSession) {
		this.rm = sqlSession.getMapper(ReplyMapper.class);
	}	
	@Override
	public ArrayList<ReplyVo> replySelectAll(int bidx,int block) {
				
		block = block*15;
		ArrayList<ReplyVo> clist =  rm.replySelectAll(bidx,block);		
		return clist;
	}
	@Override
	public int replyInsert(ReplyVo rv) {
		int value = rm.replyInsert(rv);
		return value;
	}
	@Override
	public int replyDelete(ReplyVo rv) {
		int value = rm.replyDelete(rv);
		return value;
	}
	@Override
	public int replyTotalCnt(int bidx) {
		 int cnt = rm.replyTotalCnt(bidx);
		return cnt;
	}
	
}

