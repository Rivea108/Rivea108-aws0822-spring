package com.myaws.myapp.persistance;

import java.util.ArrayList;
import java.util.HashMap;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.ReplyVo;
import com.myaws.myapp.domain.SearchCriteria;

public interface ReplyMapper {
	
	public  ArrayList<ReplyVo> commentSelectAll(int bidx,int block) ;	

	public int replyInsert(ReplyVo rv);
	
	public int replyDelete(ReplyVo rv);
	
	public int commentTotalCnt(int bidx);

	public ArrayList<ReplyVo> replySelectAll(int bidx, int block);

	public int replyTotalCnt(int bidx); 
}
