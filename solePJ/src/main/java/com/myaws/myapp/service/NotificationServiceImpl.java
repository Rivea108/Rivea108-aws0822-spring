package com.myaws.myapp.service;

import java.util.List;

import javax.management.Notification;

import org.springframework.stereotype.Service;

import com.myaws.myapp.persistance.NotificationMapper;

	@Service
	public class NotificationServiceImpl implements NotificationService {	
	
		private NotificationMapper nm;

		@Override
		public List<String> getAllChannels() {
			
			return null;
		}

		@Override
		public List<Notification> getAllNotifications() {
			
			return null;
		}

	
}
