package com.myaws.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myaws.myapp.service.SubscribeService;
import com.myaws.myapp.domain.SubscribeVo;  // SubscribeVo ����Ʈ

@Controller
@RequestMapping(value = "/subscribe/")
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    @RequestMapping(value = "subscribeAL.aws")
    public String subscribe(Model model) {
       
        try {
            List<SubscribeVo> subscribeList = subscribeService.getAllSubscribe();
            model.addAttribute("subscribeList", subscribeList);
        } catch (Exception e) {
            e.printStackTrace();  // ���� �߻� �� �α� ���
        }
        return "WEB-INF/subscribe/subscribeAL";
    }
}