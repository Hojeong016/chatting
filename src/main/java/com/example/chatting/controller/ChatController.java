package com.example.chatting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.chatting.service.RedisPublisher;

//채팅 전송 컨트롤러
@RestController
@RequestMapping("/Chat")
public class ChatController {

    @Autowired
    RedisPublisher redisPublisher;

    @PostMapping("send")
    public String sendMessage(String message) {
        redisPublisher.publish(message);
        return "메세지 보내기 성공!";
    }


}
