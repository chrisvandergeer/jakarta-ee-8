package nl.cge.jakartaee8.helloworld.entity;

import java.util.UUID;

public class HelloWorld {

    private String id = UUID.randomUUID().toString();
    private String message;

    public HelloWorld() {
        this("Hello World");
    }

    public HelloWorld(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
