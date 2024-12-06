package com.myaws.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myaws.myapp.service.BoardService;
import com.myaws.myapp.service.ChannelService;

	@Controller
	@RequestMapping(value = "/channel/")
	public class ChannelController {
	
		@Autowired(required = false)
	    private ChannelService channelService;
		
		 @RequestMapping(value = "mainChannel.aws")
		    public String channel(Model model) {
					
				return "WEB-INF/channel/mainChannel";
		}
	}

