package org.api.dto.responseDto;

import java.util.List;

public class BooksResponse {
    public List<Book> books;

    public static class Book {
        public String isbn;
        public String title;
        public String subTitle;
        public String author;
        public String publish_date;
        public String publisher;
        public Integer pages;
        public String description;
        public String website;
    }
}
