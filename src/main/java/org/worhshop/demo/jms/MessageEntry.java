package org.worhshop.demo.jms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageEntry {

    private String sender;

    private String message;

    @JsonCreator
    public MessageEntry(@JsonProperty("sender") String sender, @JsonProperty("message") String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}
