package com.myaws.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.persistance.SubscribeMapper;
import com.myaws.myapp.domain.SubscribeVo;  // SubscribeVo 임포트

@Service
public class SubscribeServiceImpl implements SubscribeService {
    
    private SubscribeMapper sm;

    @Override
    public List<SubscribeVo> getAllSubscribe() {
        // SubscribeMapper에서 구독 정보를 가져오는 로직을 추가해야 합니다.
        return sm.getAllSubscribe();  // 예시: 실제 구독 정보를 반환하는 메소드 호출
    }
}