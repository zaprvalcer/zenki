package com.minasan.zenki.models;

/**
 * Holds common data about the topic
 */
public class TopicTO {
    private UserTO author;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserTO getAuthor() {
        return author;
    }

    public void setAuthor(UserTO author) {
        this.author = author;
    }
}
