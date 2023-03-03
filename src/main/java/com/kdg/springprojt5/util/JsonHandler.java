package com.kdg.springprojt5.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.time.LocalDate;

@Component
public class JsonHandler {

    private final Logger logger;
    private final Gson gson;
    public JsonHandler() {
        this.logger = LoggerFactory.getLogger(this.getClass());
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gson = builder.create();
    }

    public <T> String saveToJson(T object, String fileName) {
        String json = gson.toJson(object);
        try (FileWriter writer = new FileWriter("src/main/resources/json/" + fileName + ".json")) {
            writer.write(json);
            logger.info("Json saved to file: " + fileName);
        } catch (Exception e) {
            logger.error("Unable to save " + object.getClass().getSimpleName() + " to file " + fileName);
            logger.error(e.getMessage());
        }

        System.out.println(json);
        return json;
    }
}


