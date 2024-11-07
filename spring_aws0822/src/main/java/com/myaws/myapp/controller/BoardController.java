package com.myaws.myapp.controller;

import java.net.InetAddress;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myaws.myapp.domain.BoardVo;
import com.myaws.myapp.domain.PageMaker;
import com.myaws.myapp.domain.SearchCriteria;
import com.myaws.myapp.service.BoardService;
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
		System.out.println(123456);
		
		String path = "WEB-INF/board/boardWrite";
		System.out.println(123);
		return path;
		
	}
	
	@RequestMapping(value="boardWriteAction.aws")
	public String boardWriteAction(
			BoardVo bv,
			@RequestParam("filename") MultipartFile filename,
			//HttpSession session,//글쓰려면 회원번호가 필요함
			HttpServletRequest request,
			RedirectAttributes rttr) throws Exception {
		MultipartFile file = filename;
		String uploadedFileName="";
		
		if(! file.getOriginalFilename().equals("")) {
			
			
			UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		
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
