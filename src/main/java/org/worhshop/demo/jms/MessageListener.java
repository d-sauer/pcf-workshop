package org.worhshop.demo.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.worhshop.demo.service.MessagesService;
import org.worhshop.demo.service.MyMessageService;

@Component
public class MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MyMessageService.class);

    private static final String messageTopicName = "demo.message.topic";

    private MessagesService messagesService;

    public MessageListener(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @JmsListener(destination = messageTopicName, containerFactory = "topicListenerFactory")
    public void receiveTopicMessage(final MessageEntry message) {
        logger.info("Receive message from: {} > {}", message.getSender(), message.getMessage());
        messagesService.add(message);
    }

}
