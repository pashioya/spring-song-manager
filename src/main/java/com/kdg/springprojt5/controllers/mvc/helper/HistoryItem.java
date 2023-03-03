package com.kdg.springprojt5.controllers.mvc.helper;

import java.time.LocalDateTime;

public class HistoryItem {
    String message;
    LocalDateTime timeStamp;

    public HistoryItem(String message, LocalDateTime timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }


    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

}
