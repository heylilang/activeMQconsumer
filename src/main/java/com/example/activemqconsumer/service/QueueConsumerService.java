package com.example.activemqconsumer.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
@Configuration
public class QueueConsumerService {
    @Value("${mq.queue}")
    private String mqQueue ;

    @Value("${mq.topic}")
    private String mqTopic ;

    @JmsListener(destination = "${mq.queue}", containerFactory = "queueListenerContainerFactory")
    public void receive3(Message message, Session session) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        System.out.println("queue 消费者 receive3 = " + textMessage.getText());

        try {
            if (textMessage.getText().contains("MQ.Queue")){
                throw new JMSException("故意抛出的异常");
            }
            message.acknowledge();

        } catch (JMSException e) {
            System.out.println(String.format("触发重发机制msg = %s", textMessage.getText()));
            session.recover();
        }

    }


}
