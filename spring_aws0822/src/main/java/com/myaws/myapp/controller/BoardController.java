package com.myaws.myapp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
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

@Controller
@RequestMapping(value="/board/")
public class BoardController {
	
	@Autowired(required=false)
	private BoardService boardService;

	@Autowired(required=false)
	private PageMaker pm;
	
	@Resource(name ="uploadPath")//이름을 통해서 주입하는방식
	private String uploadPath;
	
	@RequestMapping(value="boardList.aws")
	public String boardList(SearchCriteria scri, Model model) {
	    // 배경 설정만 하고 데이터를 전달하지 않음
		
		int cnt = boardService.boardTotalCount(scri);
		pm.setScri(scri);
		pm.setTotalCount(cnt);
		
		ArrayList<BoardVo> blist =  boardService.boardSelectAll(scri);
		
		model.addAttribute("blist", blist);		
		model.addAttribute("pm", pm);	
		
		String path = "WEB-INF/board/boardList";  // 배경 설정만 포함된 JSP 페이지 경로
	    return path;  // 배경만 있는 JSP 페이지 반환
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
			//HttpSession session,//글쓰려면 회원번호가 필요함
			HttpServletRequest request,
			RedirectAttributes rttr
			) throws Exception {
		
		//
		
		MultipartFile file = attachfile;
		String uploadedFileName="";
		
		if(! file.getOriginalFilename().equals("")) {
			
			
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			//uploadedFileName = 이름을 받아오는 부분이 없어서 안됨		
		
		}
		
		
		String midx = request.getSession().getAttribute("midx").toString();//3교시session -> request.getSession() 
		int midx_int = Integer.parseInt(midx);//3교시
		
		String ip = getUserIp(request);
		
		bv.setUploadedFilename(uploadedFileName);
		bv.setMidx(midx_int);
		bv.setIp(ip);
		
		int value = boardService.boardInsert(bv);
		
		String path="";
		if(value == 2) {
			path="redirect:/board/boardList.aws";
		}else {
			rttr.addFlashAttribute("msg","입력이 잘못되었습니다");
			path="redirect:/board/boardWrite.aws";
		}
		return path;
	}
	
	@RequestMapping(value="boardContents.aws")
	public String boardContents(@RequestParam("bidx") int bidx, Model model) {	
		
		boardService.boardViewCntUpdate(bidx);
		BoardVo bv = boardService.boardSelectOne(bidx);
		
		model.addAttribute("bv", bv);
		
		String path="WEB-INF/board/boardContents";		
		return path;
	}
	
	
	//모르겠으니 물어보기
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
	// http:@RequestMapping(value="boardRecom.aws이거에서 method=RequestMethod.GET 추가로 적는 이유는 
	// http://localhost/board/boardList.aws(리스트)에서 게시글을 클릭했을때 http://localhost/board/boardContents.aws?bidx=1063
	// ?뒤에 있는 부분은 겟 메소드라고 한다 그 겟 메소드를 받기 위해 method=RequestMethod.GET을 적는 것
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
		model.addAttribute("bv", bv);//왜 이걸 bv로 변경했을까?
		
		String path="WEB-INF/board/boardModify";
		return path;
	}
	
	@RequestMapping(value="boardModifyAction.aws")	
	public String boardModifyAction(
			 // @RequestParam("bidx")int bidx, 
			 // @RequestParam("subject") String subject, //하나씩 넘겨도됨 하지만 한번에 하는게 제일 좋다
			 // Model model) {
			BoardVo bv,
			@RequestParam("attachfile") MultipartFile attachfile,
			HttpServletRequest request,
			
			//추가본
			RedirectAttributes rttr) throws IOException, Exception { 
				
	int value=0;
	
	
	MultipartFile file = attachfile;
	String uploadedFileName="";
	if(! file.getOriginalFilename().equals("")) {
		uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
	}
	
	String midx = request.getSession().getAttribute("midx").toString();
	int midx_int = Integer.parseInt(midx);
	String ip = getUserIp(request);
	
	bv.setUploadedFilename(uploadedFileName); //FileName 컬럼값으로 넣을려고
	bv.setMidx(midx_int);
	bv.setIp(ip);
	//추가본

	
	// 파일 업로드를 하고 update를 하기 위한 service를 만든다.
	value = boardService.boardUpdate(bv);	
	
	
	String path="";	
		if(value==0) {
			rttr.addFlashAttribute("msg", "답변이 등록되지 않았습니다");
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
			
			//추가본
			RedirectAttributes rttr) throws IOException, Exception { 
				
	int value=0;
	
	MultipartFile file = attachfile;
	String uploadedFileName="";
	if(! file.getOriginalFilename().equals("")) {
		uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
	}
	
	String midx = request.getSession().getAttribute("midx").toString();
	int midx_int = Integer.parseInt(midx);
	String ip = getUserIp(request);
	
	bv.setUploadedFilename(uploadedFileName); //FileName 컬럼값으로 넣을려고
	bv.setMidx(midx_int);
	bv.setIp(ip);

	// 파일 업로드를 하고 update를 하기 위한 service를 만든다.
	int maxBidx = 0; 
	maxBidx = boardService.boardReply(bv);
	
	String path="";	
		if(maxBidx != 0) {
			path="redirect:/board/boardContents.aws?bidx="+maxBidx;
		}else {
			rttr.addAttribute("msg", "답변이 등록되지 않았습니다.");
			path="redirect:/board/boardReply.aws?bidx="+bv.getBidx();	
			}
		
		
		return path;
	}
	
	
public String getUserIp(HttpServletRequest request) throws Exception {
		
        String ip = null;
      //  HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        ip = request.getHeader("X-Forwarded-For");
        
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-Real-IP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-RealIP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }        
        
        if (ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) {
        	InetAddress address = InetAddress.getLocalHost();
        	ip = address.getHostAddress();
        }        
		
		return ip;
	}
}
