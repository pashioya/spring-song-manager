package com.kdg.springprojt5.controllers.mvc.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataItem {
    private final Logger logger;

    //Represents the possible options of types
    private final String type;
    private final String content;

    public DataItem(String type, String content) {
        logger = LoggerFactory.getLogger(this.getClass());
        this.type = type;
        this.content = content;
    }

    public DataItem(String type) {
        logger = LoggerFactory.getLogger(this.getClass());
        this.type = type;
        this.content = "";
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
