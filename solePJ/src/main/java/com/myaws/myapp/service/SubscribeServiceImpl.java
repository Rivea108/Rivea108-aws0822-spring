package com.myaws.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.persistance.SubscribeMapper;
import com.myaws.myapp.domain.SubscribeVo;  // SubscribeVo ����Ʈ

@Service
public class SubscribeServiceImpl implements SubscribeService {
    
    private SubscribeMapper sm;

    @Override
    public List<SubscribeVo> getAllSubscribe() {
        // SubscribeMapper���� ���� ������ �������� ������ �߰��ؾ� �մϴ�.
        return sm.getAllSubscribe();  // ����: ���� ���� ������ ��ȯ�ϴ� �޼ҵ� ȣ��
    }
}