package com.example.chatting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import com.example.chatting.service.RedisSubscriber;
//RedisTemplate과 Redis 메시지 리스너 설정

@Configuration
public class RedisConfig {

    //RedisTemplate 설정
    //레디스에 정보를 보대고 읽기 위해 사용하는 기본 도구
    //key-value 형태의 데이터를 처리하며 레디스에 메시지를 주거나 데이터를 저장할 때 사용
    //Redis 연결 팩토리를 사용 레디스 서버와의 연결을 설정
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return  redisTemplate;
    }

    //Redis Pub/Sub 메시지를 수신하기 위한 리스너 컨테이너
    //RonnectionFactory는 Redis와의 연결을 설정
    //addMessageListener() 메서드는 특정 토픽에 대해 메시지 리스너를 등록
    //listenerAdapter는 메시지를 실제로 처리하는 리스너 객체입니다.
    //topic()은 메시지를 수신할 Redis 채널(토픽)을 지정합니다. 이 코드에서는 chat이라는 채널을 구독하고 있습니다.
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListenerAdapter,topic());
        return container;
    }

    //메시지가 수신될 때마다 onMessage() 메서드가 호출
    @Bean
    public MessageListenerAdapter listenerAdapter(RedisSubscriber redisSubscriber) {
        return  new MessageListenerAdapter(redisSubscriber, "onMessage");
    }

    @Bean
    public ChannelTopic topic(){
        return new ChannelTopic("topic");
    }
}
