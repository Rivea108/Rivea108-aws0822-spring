package com.myaws.myapp.service;

import java.util.List;
import com.myaws.myapp.domain.SubscribeVo;  // SubscribeVo ����Ʈ

public interface SubscribeService {

    List<SubscribeVo> getAllSubscribe();  // ��ȯ Ÿ���� SubscribeVo�� ����
}