package com.myaws.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {//�߰�ȣ ���� 
		//preHandle : �����ϱ����� ����æ��(���̵� �ִ��� ������ üũ)
		
		
		//�����ο� �ش� �޼ҵ� ���� ���� ����ä��
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("midx") == null) {
		//�α����� �ȵǾ��ִٸ� �̵��Ϸ����ϴ� �ּҸ� �����ϰ�
		//�α����������� ������

			saveUrl(request);//�̵��� ��θ� �����Ѵ�.
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
	public void saveUrl(HttpServletRequest request) {//���̵�� ���ϰ��� ����
	
	String uri = request.getRequestURI(); //��ü��� �ּ�
	String param = request.getQueryString(); //�Ķ���͸� �����´�.
	
	if(param == null || param.equals("null")|| param.equals("")) {
		param="";
	}else {
		param="?";
	}
	
	//�̵��� ������
	String  locationUrl = uri + param;
	
	HttpSession session = request.getSession();
	if(request.getMethod().equals("GET")) { //�빮�� GET
		session.setAttribute("saveUrl", locationUrl);
	}
	
	}	
}
