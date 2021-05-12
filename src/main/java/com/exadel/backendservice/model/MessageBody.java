package com.exadel.backendservice.model;

import java.time.LocalDateTime;
import java.util.List;

public class MessageBody {
    private StringBuilder body;

    public MessageBody(String fullName, List<MessageBodyBase> messageBodyBases){
        this(fullName, messageBodyBases, null);
    }

    public MessageBody(String fullName, List<MessageBodyBase> messageBodyBases, LocalDateTime dateTime){
        body = new StringBuilder();
        body.append(fullName);
        for (MessageBodyBase messageBodyBase:messageBodyBases) {
            body.append(messageBodyBase.getBodyBase());
        }
        if(dateTime!=null) {
            body.append(dateTime.getDayOfMonth());
            body.append("/");
            body.append(dateTime.getMonth().getValue());
            body.append("/");
            body.append(dateTime.getYear());
            body.append(" at ");
            body.append(dateTime.getHour());
            body.append(":");
            body.append(dateTime.getMinute());
            body.append(".");
        }
    }

    public String getBody() {
        return body.toString();
    }
}
