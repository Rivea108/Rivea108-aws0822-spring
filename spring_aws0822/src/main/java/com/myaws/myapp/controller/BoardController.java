package com.myaws.myapp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.PageMaker;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.service.BoardService;
import com.myaws.myapp.util.MediaUtils;
import com.myaws.myapp.util.UploadFileUtiles;
import com.myaws.myapp.util.UserIp;

@Controller
@RequestMapping(value="/board/")
public class BoardController {
	
	@Autowired(required=false)
	private BoardService boardService;

	@Autowired(required=false)
	private PageMaker pm;
	
	@Resource(name ="uploadPath")//�̸��� ���ؼ� �����ϴ¹��
	private String uploadPath;
	
	@Autowired(required=false)	
	private UserIp userip;
	
	@RequestMapping(value="boardList.aws")
	public String boardList(SearchCriteria scri, Model model) {
	    // ��� ������ �ϰ� �����͸� �������� ����
		
		int cnt = boardService.boardTotalCount(scri);
		pm.setScri(scri);
		pm.setTotalCount(cnt);
		
		ArrayList<BoardVo> blist =  boardService.boardSelectAll(scri);
		
		model.addAttribute("blist", blist);		
		model.addAttribute("pm", pm);	
		
		String path = "WEB-INF/board/boardList";  // ��� ������ ���Ե� JSP ������ ���
	    return path;  // ��游 �ִ� JSP ������ ��ȯ
	}
	
	@RequestMapping(value="boardWrite.aws")
	public String boardWrite() {
		
		String path = "WEB-INF/board/boardWrite";
		return path;
		
	}
	
	@RequestMapping(value="boardWriteAction.aws")
	public String boardWriteAction(
			BoardVo bv,
			@RequestParam("attachfile") MultipartFile attachfile,
			//HttpSession session,//�۾����� ȸ����ȣ�� �ʿ���
			HttpServletRequest request,
			RedirectAttributes rttr
			) throws Exception {
		
		//
		
		MultipartFile file = attachfile;
		String uploadedFileName="";
		
		if(! file.getOriginalFilename().equals("")) {
			
			
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			//uploadedFileName = �̸��� �޾ƿ��� �κ��� ��� �ȵ�		
		
		}
		
		
		String midx = request.getSession().getAttribute("midx").toString();//3����session -> request.getSession() 
		int midx_int = Integer.parseInt(midx);//3����
		
		String ip = userip.getUserIp(request);
		
		bv.setUploadedFilename(uploadedFileName);
		bv.setMidx(midx_int);
		bv.setIp(ip);
		
		int value = boardService.boardInsert(bv);
		
		String path="";
		if(value == 2) {
			path="redirect:/board/boardList.aws";
		}else {
			rttr.addFlashAttribute("msg","�Է��� �߸��Ǿ����ϴ�");
			path="redirect:/board/boardWrite.aws";
		}
		return path;
	}
	
	//===============================
	@ResponseBody
	@PostMapping("/boardWriteActionReact.aws") //����Ʈ�� ���� �鿣�� �ּ� ����
	public JSONObject boardWriteActionReact(@RequestBody BoardVo bv) {
		System.out.println("���� : " + bv.getMidx());
		
		//ResponseEntity<BoardVo> entity = null;
		
		JSONObject js = new JSONObject();
		int value = boardService.boardInsert(bv);
		System.out.println("value : " + value);
		
		if(value==2) { //����
			js.put("result", "success");
		}else { //����
			js.put("result", "fail");
		}
		
			return js;
	}
	
	//================================
	
	@RequestMapping(value="boardContents.aws")
	public String boardContents(@RequestParam("bidx") int bidx, Model model) {	
		
		boardService.boardViewCntUpdate(bidx);
		BoardVo bv = boardService.boardSelectOne(bidx);
		
		model.addAttribute("bv", bv);
		
		String path="WEB-INF/board/boardContents";		
		return path;
	}
	
	
	//�𸣰����� �����
	@RequestMapping(value = "/displayFile.aws", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(
	    @RequestParam("fileName") String fileName,
	    @RequestParam(value = "down", defaultValue = "0") int down
	) {
	    ResponseEntity<byte[]> entity = null;
	    InputStream in = null;

	    try{
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();		
			 
			in = new FileInputStream(uploadPath+fileName);
			
			
			if(mType != null){
				
				if (down==1) {
					fileName = fileName.substring(fileName.indexOf("_")+1);
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					headers.add("Content-Disposition", "attachment; filename=\""+
							new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");	
					
				}else {
					headers.setContentType(mType);	
				}
				
			}else{
				
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\""+
						new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");				
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					headers,
					HttpStatus.CREATED);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	    return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="boardRecom.aws",method=RequestMethod.GET)
	// http:@RequestMapping(value="boardRecom.aws�̰ſ��� method=RequestMethod.GET �߰��� ���� ������ 
	// http://localhost/board/boardList.aws(����Ʈ)���� �Խñ��� Ŭ�������� http://localhost/board/boardContents.aws?bidx=1063
	// ?�ڿ� �ִ� �κ��� �� �޼ҵ��� �Ѵ� �� �� �޼ҵ带 �ޱ� ���� method=RequestMethod.GET�� ���� ��
	public JSONObject boardRecom(@RequestParam("bidx")int bidx){
		
		
		int value = boardService.boardRecomUpdate(bidx);
		
		JSONObject js = new JSONObject();
		js.put("recom", value);
		
		
		return js;
	}
	
	@RequestMapping(value="boardDelete.aws")
	public String boardDelete(@RequestParam("bidx")int bidx, Model model) {
		
		
		model.addAttribute("bidx", bidx);
		String path="WEB-INF/board/boardDelete";
		return path;
	}
	
	
	
	@RequestMapping(value="boardDeleteAction.aws",method=RequestMethod.POST)
	public String boardDelete(
			@RequestParam("bidx")int bidx,
			@RequestParam("password")String password,
			HttpSession session){
		
		int midx = Integer.parseInt(session.getAttribute("midx").toString());
		int value = boardService.boardDelete(bidx,midx,password);
		
		String path="redirect:/board/boardList.aws";
		if (value == 0) {
			path="redirect:/board/boardDelete.aws?bidx="+bidx;
		}
		
		return path;
	}
	
	@RequestMapping(value="boardModify.aws")
	public String boardModify(@RequestParam("bidx")int bidx, Model model) {
		
		BoardVo bv = boardService.boardSelectOne(bidx);
		model.addAttribute("bv", bv);//�� �̰� bv�� ����������?
		
		String path="WEB-INF/board/boardModify";
		return path;
	}
	
	@RequestMapping(value="boardModifyAction.aws")	
	public String boardModifyAction(
			 // @RequestParam("bidx")int bidx, 
			 // @RequestParam("subject") String subject, //�ϳ��� �Ѱܵ��� ������ �ѹ��� �ϴ°� ���� ����
			 // Model model) {
			BoardVo bv,
			@RequestParam("attachfile") MultipartFile attachfile,
			HttpServletRequest request,
			
			//�߰���
			RedirectAttributes rttr) throws IOException, Exception { 
				
	int value=0;
	
	
	MultipartFile file = attachfile;
	String uploadedFileName="";
	if(! file.getOriginalFilename().equals("")) {
		uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
	}
	
	String midx = request.getSession().getAttribute("midx").toString();
	int midx_int = Integer.parseInt(midx);
	String ip = userip.getUserIp(request);
	
	bv.setUploadedFilename(uploadedFileName); //FileName �÷������� ��������
	bv.setMidx(midx_int);
	bv.setIp(ip);
	//�߰���

	
	// ���� ���ε带 �ϰ� update�� �ϱ� ���� service�� �����.
	value = boardService.boardUpdate(bv);	
	
	
	String path="";	
		if(value==0) {
			rttr.addFlashAttribute("msg", "�亯�� ��ϵ��� �ʾҽ��ϴ�");
			path="redirect:/board/boardModify.aws?bidx="+bv.getBidx();
		}else {
			path="redirect:/board/boardContents.aws?bidx="+bv.getBidx();	
			}
		
		
		return path;
	}
	
	@RequestMapping(value="boardReply.aws")
	public String boardReply(@RequestParam("bidx")int bidx, Model model) {
		
		BoardVo bv = boardService.boardSelectOne(bidx);
		model.addAttribute("bv", bv);
		
		
		String path="WEB-INF/board/boardReply";
		return path;
	}
	
	
	@RequestMapping(value="boardReplyAction.aws")	
	public String boardReplyAction(
			BoardVo bv,
			@RequestParam("attachfile") MultipartFile attachfile,
			HttpServletRequest request,
			
			//�߰���
			RedirectAttributes rttr) throws IOException, Exception { 
				
	int value=0;
	
	MultipartFile file = attachfile;
	String uploadedFileName="";
	if(! file.getOriginalFilename().equals("")) {
		uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
	}
	
	String midx = request.getSession().getAttribute("midx").toString();
	int midx_int = Integer.parseInt(midx);
	String ip = userip.getUserIp(request);
	
	bv.setUploadedFilename(uploadedFileName); //FileName �÷������� ��������
	bv.setMidx(midx_int);
	bv.setIp(ip);

	// ���� ���ε带 �ϰ� update�� �ϱ� ���� service�� �����.
	int maxBidx = 0; 
	maxBidx = boardService.boardReply(bv);
	
	String path="";	
		if(maxBidx != 0) {
			path="redirect:/board/boardContents.aws?bidx="+maxBidx;
		}else {
			rttr.addAttribute("msg", "�亯�� ��ϵ��� �ʾҽ��ϴ�.");
			path="redirect:/board/boardReply.aws?bidx="+bv.getBidx();	
			}
		
		
		return path;
	}
}
	
