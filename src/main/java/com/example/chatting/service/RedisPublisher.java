package com.example.chatting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

//레디스에 메세지를 발행하기
//pup (발행자)
@Service
public class RedisPublisher {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ChannelTopic channelTopic;

    public void publish(String message) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
}

