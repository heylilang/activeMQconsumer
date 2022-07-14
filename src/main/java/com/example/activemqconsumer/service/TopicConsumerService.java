package com.example.activemqconsumer.service;


import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class TopicConsumerService {
    @JmsListener(destination="${mq.topic}", containerFactory = "topicListenerContainerFactory")
    public void receive1(String text){
        System.out.println("topic 消费者:receive1= " + text);
    }
    @JmsListener(destination="${mq.topic}", containerFactory = "topicListenerContainerFactory")
    public void receive2(String text){
        System.out.println("topic 消费者:receive2= " + text);
    }

}
