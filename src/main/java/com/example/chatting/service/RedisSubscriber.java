package com.example.chatting.service;

import org.springframework.stereotype.Service;

//sup 수신자
//실제 메세지가 처리되는 로직을 담당
//메시지와 채널 이름을 파라미터로 받아서 사용
@Service
public class RedisSubscriber {
    public void onMessage(String message, String channel) {
        System.out.println("메세지 내용 : "+ message + "채널 : " + channel);
    }
}
