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
		    private NotificationService notificationService;  // 알림 서비스를 주입
		   
		    @RequestMapping(value = "notificationAL.aws")
		    public String notification(Model model) {
		    	
		        // 알림 데이터를 가져옵니다 (Service에서 반환된 데이터)
		        List<Notification> notifications = notificationService.getAllNotifications();
		       
		        // DB에서 할거라 코드를 적을 일이 없음 = 알림을 시간순으로 정렬 (최신 것이 위로 오게)

		        // 모델에 알림 데이터를 추가
		        model.addAttribute("notifications", notifications);
		       
		        // 뷰 이름 반환
		        return "WEB-INF/notification/notificationAL";  // notification.jsp 또는 notification.html (뷰 이름)
		    }
		}