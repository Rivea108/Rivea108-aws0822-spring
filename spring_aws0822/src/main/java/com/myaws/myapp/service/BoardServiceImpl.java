package com.myaws.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.persistance.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	private BoardMapper bm;
	
	@Autowired
	public BoardServiceImpl(SqlSession sqlSession) {
		this.bm = sqlSession.getMapper(BoardMapper.class);
	}
	
	
	@Override
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri) {
		
		HashMap<String,Object> hm = new HashMap<String,Object>();		
		hm.put("startPageNum", (scri.getPage()-1)* scri.getPerPageNum());
		hm.put("perPageNum", scri.getPerPageNum());
		hm.put("searchType", scri.getSearchType());
		hm.put("keyword", scri.getKeyword());		
		
		ArrayList<BoardVo> blist =  bm.boardSelectAll(hm);		
		return blist;
	}
	@Override
	public int boardTotalCount(SearchCriteria scri) {	
				
		int cnt = bm.boardTotalCount(scri);
		return cnt;
	}
	
	@Override
	@Transactional
	public int boardInsert(BoardVo bv) {
	
		int value = bm.boardInsert(bv);
		int maxBidx = bv.getBidx();
		int value2 = bm.boardOriginbidxUpdate(maxBidx);
		
		return value+value2;
	}


	@Override
	public BoardVo boardSelectOne(int bidx) {
		BoardVo bv = bm.boardSelectOne(bidx);		
		return bv;
	}


	@Override
	public int boardViewCntUpdate(int bidx) {
		int cnt = bm.boardViewCntUpdate(bidx);
		return cnt;
	}


	@Override
	public int boardRecomUpdate(int bidx) {
		BoardVo bv = new BoardVo();
		bv.setBidx(bidx);
		int cnt = bm.boardRecomUpdate(bv);
		int recom = bv.getRecom();
		return recom;
	}


	@Override
	public int boardDelete(int bidx, int midx, String password) {//매개변수가 3개이기에 해쉬맵을 사용하기로한다
		
		//boardMapper.java에 해쉬맵 지정(24줄)
		HashMap<String,Object> hm = new HashMap<String,Object>(); //해쉬맵
		hm.put("bidx", bidx);
		hm.put("midx", midx);
		hm.put("password", password);
		
		int cnt = bm.boardDelete(hm);
		
		return cnt;
	}


	@Override
	public int boardUpdate(BoardVo bv) {
		
		int value = bm.boardUpdate(bv);
		return value;
		
	}

	@Transactional
	@Override
	public int boardReply(BoardVo bv) {
		//업데이트하고 입력하기 만들기(메소드 2개)
		int value = bm.boardReplyUpdate(bv);
		int value2 = bm.boardReplyInsert(bv);
		int maxBidx = bv.getBidx();// 안썼음
		
		return maxBidx;
	}


	@Override
	public int boardInsertReact(BoardVo bv) {
		
		int value = bm.boardInsert(bv);
		int maxBidx = bv.getBidx();
		int value2 = bm.boardOriginbidxUpdate(maxBidx);
		
		return value+value2;
	}


	
}

