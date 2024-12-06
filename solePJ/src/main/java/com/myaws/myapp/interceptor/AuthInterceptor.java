package com.myaws.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {//중괄호 문제 
		//preHandle : 접근하기전에 가로챈다(아이디가 있는지 없는지 체크)
		
		
		//가상경로에 해당 메소드 접근 전에 가로채기
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("midx") == null) {
		//로그인이 안되어있다면 이동하려고하는 주소를 보관하고
		//로그인페이지로 보낸다

			saveUrl(request);//이동할 경로를 저장한다.
			response.sendRedirect(request.getContextPath()+"/member/memberLogin.aws");
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}
	public void saveUrl(HttpServletRequest request) {//보이드는 리턴값이 없다
	
	String uri = request.getRequestURI(); //전체경로 주소
	String param = request.getQueryString(); //파라미터를 가져온다.
	
	if(param == null || param.equals("null")|| param.equals("")) {
		param="";
	}else {
		param="?";
	}
	
	//이동할 페이지
	String  locationUrl = uri + param;
	
	HttpSession session = request.getSession();
	if(request.getMethod().equals("GET")) { //대문자 GET
		session.setAttribute("saveUrl", locationUrl);
	}
	
	}	
}
