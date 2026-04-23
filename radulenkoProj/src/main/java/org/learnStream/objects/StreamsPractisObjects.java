package org.learnStream.objects;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsPractisObjects {
    public static void main(String[] args) {


        Author author1 = new Author("Alice", "Smith", 30);
        Author author2 = new Author("Bob", "Johnson", 40);
        Author author3 = new Author("Charlie", "Williams", 50);

        Book book1 = new Book("Book1", 150, 19.99, Arrays.asList(author1, author2));
        Book book2 = new Book("Book2", 50, 9.99, Arrays.asList(author1));
        Book book3 = new Book("Book3", 200, 29.99, Arrays.asList(author2, author3));

        List<Book> books = Arrays.asList(book1, book2, book3);


        System.out.println("------ 1 ----------");
//        вибрати всі книги, у яких більше 100 сторінок,
//        і автори, імена яких починаються на певну літеру:

//        List<Book> filteredBooks = books.stream()
//                .filter(book -> book.getNumberOfPages() > 100)
//                .filter(book -> book.getAuthors().stream().anyMatch(author -> author.getName().startsWith("A")))
//                .collect(Collectors.toList());


        List<Book> filteredBooks =
                books.stream()
                                .filter(book -> book.getNumberOfPages()>100)
                                        .filter(book -> book.getAuthors().stream().anyMatch(author -> author.getName().startsWith("A")))
                                                .collect(Collectors.toList());
//        System.out.println(filteredBooks);
        filteredBooks.forEach(System.out::println);

    }
}
