package com.myaws.myapp.service;

import java.util.List;

import org.apache.catalina.tribes.Channel;

public interface ChannelService {

	List<Channel> getAllChannel();

	List<String> getAllChannels();

}
