package com.myaws.myapp.service;

import java.util.List;

import org.apache.catalina.tribes.Channel;
import org.springframework.stereotype.Service;

import com.myaws.myapp.persistance.ChannelMapper;
import com.myaws.myapp.persistance.NotificationMapper;

	@Service
	public class ChannelServiceImpl implements ChannelService {	
	
		private ChannelMapper nm;

		@Override
		public List<Channel> getAllChannel() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<String> getAllChannels() {
			// TODO Auto-generated method stub
			return null;
		}

		
		}