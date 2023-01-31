package com.example.guide;

public class Greeting {
    long id;
    String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    // Need public getters to return to client
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
