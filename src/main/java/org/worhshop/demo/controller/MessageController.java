package org.worhshop.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.worhshop.demo.jpa.MyMessage;
import org.worhshop.demo.jms.MessageEntry;
import org.worhshop.demo.service.MessagesService;
import org.worhshop.demo.service.MyMessageService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MyMessageService myMessageService;

    @Autowired
    private MessagesService messagesService;

    @GetMapping("my-messages")
    public List<String> getMyMessages() {
        return myMessageService
                .list()
                .stream()
                .map(MyMessage::getMessage)
                .collect(Collectors.toList());
    }

    @GetMapping("messages")
    public List<MessageEntry> allMessages() {
        return messagesService.list();
    }

    @PostMapping("message")
    public ResponseEntity sendMessage(@RequestBody RequestMessage message) {
        myMessageService.add(message.getMessage());

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
