package com.myaws.myapp.service;

import java.util.ArrayList;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.ReplyVo;
import com.myaws.myapp.domain.ReplyVo;
import com.myaws.myapp.domain.SearchCriteria;

public interface ReplyService {
	
	public  ArrayList<ReplyVo> replySelectAll(int bidx,int block) ;	
	
	public int replyInsert(ReplyVo rv);
	
	public int replyDelete(ReplyVo rv);
	
	public int replyTotalCnt(int bidx); 
	
	
}
