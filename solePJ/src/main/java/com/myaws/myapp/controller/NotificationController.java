package com.myaws.myapp.controller;

import java.util.List;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.myaws.myapp.service.NotificationService;

	@Controller
	@RequestMapping(value = "/notification/")
	public class NotificationController {
		
		   @Autowired(required = false)
		    private NotificationService notificationService;  // �˸� ���񽺸� ����
		   
		    @RequestMapping(value = "notificationAL.aws")
		    public String notification(Model model) {
		    	
		        // �˸� �����͸� �����ɴϴ� (Service���� ��ȯ�� ������)
		        List<Notification> notifications = notificationService.getAllNotifications();
		       
		        // DB���� �ҰŶ� �ڵ带 ���� ���� ���� = �˸��� �ð������� ���� (�ֽ� ���� ���� ����)

		        // �𵨿� �˸� �����͸� �߰�
		        model.addAttribute("notifications", notifications);
		       
		        // �� �̸� ��ȯ
		        return "WEB-INF/notification/notificationAL";  // notification.jsp �Ǵ� notification.html (�� �̸�)
		    }
		}