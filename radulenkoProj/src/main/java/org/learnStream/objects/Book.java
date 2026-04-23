package org.learnStream.objects;

import java.util.List;

public class Book {
    private String title;
    private int numberOfPages;
    private double price;
    private List<Author> authors;

    // Конструктор, гетери та сетери
    public Book(String title, int numberOfPages, double price, List<Author> authors) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.price = price;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public double getPrice() {
        return price;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", price=" + price +
                ", authors=" + authors +
                '}';
    }
}
