package com.myaws.myapp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myaws.myapp.domain.ReplyVo;
import com.myaws.myapp.domain.ReplyVo;
import com.myaws.myapp.service.ReplyService;
import com.myaws.myapp.util.UserIp;

@RestController
@RequestMapping(value="/reply")
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	@Autowired(required=false)
	private UserIp userIp;
	
	@RequestMapping(value="/{bidx}/{block}/replyList.aws")
	public JSONObject replyList(	
			@PathVariable("bidx") int bidx,
			@PathVariable("block") int block){	
		
		
		String moreView="";	
		int nextBlock=0;
		int cnt = replyService.replyTotalCnt(bidx);
		
		if (cnt >block*15) {
			moreView = "Y";
			nextBlock = block+1;
		}else {
			moreView = "N";
			nextBlock = block;
		}		
		
		
		ArrayList<ReplyVo> clist =  replyService.replySelectAll(bidx, block);
		
		JSONObject js = new JSONObject();
		js.put("clist", clist);	
		js.put("moreView", moreView);
		js.put("nextBlock", nextBlock);
		
		
		
		return js;
		
		
		
	}
	
	@RequestMapping(value="/replyWriteAction.aws",method=RequestMethod.POST)
	public JSONObject replyWriteAction(ReplyVo rv,HttpServletRequest request) throws Exception{		
		
		
		rv.setRip(userIp.getUserIp(request));
		
		int value =  replyService.replyInsert(rv);
		JSONObject js = new JSONObject();
		js.put("value", value);	
		
		return js;
	}
	
	@RequestMapping(value="/{ridx}/replyDeleteAction.aws")
	public JSONObject replyList(
			@PathVariable("ridx") int ridx,
			HttpServletRequest request,
			ReplyVo rv) throws Exception{		
		
		int midx = Integer.parseInt(request.getSession().getAttribute("midx").toString());
		rv.setMidx(midx);
		rv.setRidx(ridx);
		rv.setRip(userIp.getUserIp(request));
		
		int value =  replyService.replyDelete(rv);
		JSONObject js = new JSONObject();
		js.put("value", value);	
		
		return js;
	}
	
}
