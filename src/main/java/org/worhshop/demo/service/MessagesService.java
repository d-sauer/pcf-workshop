package org.worhshop.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.worhshop.demo.jms.MessageEntry;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {

    private static final Logger logger = LoggerFactory.getLogger(MyMessageService.class);

    private final List<MessageEntry> messages = new ArrayList<>();

    public void add(MessageEntry message) {
        messages.add(message);
    }

    public List<MessageEntry> list() {
        return messages;
    }

}
