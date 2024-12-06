package com.myaws.myapp.service;

import java.util.List;
import com.myaws.myapp.domain.SubscribeVo;  // SubscribeVo 임포트

public interface SubscribeService {

    List<SubscribeVo> getAllSubscribe();  // 반환 타입을 SubscribeVo로 수정
}