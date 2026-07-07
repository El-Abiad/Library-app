package com.elabiad.model;

public class Book {
    private int id;
    private final String title;
    private final String author;
    private final int publishedYear;

    public Book(int id, String title, String author, int publishedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }
    public Book(String title, String author, int publishedYear) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    @Override
    public String toString(){
        return String.format(
                "%-5d %-30s %-20s %-5d",
                this.id,
                this.title,
                this.author,
                this.publishedYear
        );
    }
}
