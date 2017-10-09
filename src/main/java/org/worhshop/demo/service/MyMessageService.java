package org.worhshop.demo.service;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.worhshop.demo.jms.MessageEntry;
import org.worhshop.demo.jpa.MyMessage;
import org.worhshop.demo.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Topic;

@Service
public class MyMessageService {

    private static final Logger logger = LoggerFactory.getLogger(MyMessageService.class);

    @Value("${vcap.application.name:demo}")
    private String sender;

    private String messageTopicName = "demo.message.topic";

    private Topic messageTopic = new ActiveMQTopic(messageTopicName);

    private MessageRepository repository;

    private JmsTemplate jmsTemplate;

    public MyMessageService(MessageRepository repository, JmsTemplate jmsTemplate) {
        this.repository = repository;
        this.jmsTemplate = jmsTemplate;
    }

    public Long add(String message) {
        MyMessage msg = new MyMessage(message);
        MyMessage save = repository.save(msg);

        logger.info("Message saved to database ({})", message);

        logger.debug("Send JMS message");
        jmsTemplate.convertAndSend(messageTopic, new MessageEntry(sender, message));

        return save.getId();
    }

    public List<MyMessage> list() {
        Iterable<MyMessage> allMessages = repository.findAll();
        List<MyMessage> messages = new ArrayList<>();
        allMessages.forEach(messages::add);

        return messages;
    }
}
