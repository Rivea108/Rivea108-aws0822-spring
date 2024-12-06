package com.myaws.myapp.service;

import java.util.List;

import javax.management.Notification;

public interface NotificationService {

	List<String> getAllChannels();
	List<Notification> getAllNotifications();
}
