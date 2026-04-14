package org.learnStream.objects;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsPractisObjects2 {
    public static void main(String[] args) {

        Author author1 = new Author("Alice", "Smith", 30);
        Author author2 = new Author("Bob", "Johnson", 40);
        Author author3 = new Author("Charlie", "Williams", 50);
        Author author4 = new Author("David", "Brown", 60);

        Book book1 = new Book("Book1", 150, 19.99, Arrays.asList(author1, author2));
        Book book2 = new Book("Book2", 50, 9.99, Arrays.asList(author1));
        Book book3 = new Book("Book3", 200, 29.99, Arrays.asList(author2, author3));
        Book book4 = new Book("Book4", 100, 14.99, Arrays.asList(author4));

        List<Book> books = Arrays.asList(book1, book2, book3, book4);

        System.out.println("------ 1 ----------");
//       Вивести всі книжки, у яких автори є унікальними в порівнянні з іншими книжками..

       List<Author> listDistinctAuthors =
               books.stream().flatMap(book -> book.getAuthors().stream()).distinct().collect(Collectors.toList());

        System.out.println(listDistinctAuthors);


        List<String> authorOneBook = books.stream()
                .flatMap(book -> book.getAuthors().stream())
                .collect(Collectors.groupingBy(Author::getName, Collectors.counting()))
                .entrySet().stream().filter(v -> v.getValue() == 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList())
                ;

        System.out.println("test");
        System.out.println(authorOneBook);

        List<Book> booksWithOneAuthor = books.stream()
                .filter(book -> book.getAuthors().stream().allMatch(author -> authorOneBook.contains(author.getName())))
                .collect(Collectors.toList());
        System.out.println(booksWithOneAuthor.size());
        booksWithOneAuthor.forEach(book -> System.out.println(book.getTitle()));

//        // Створюємо список всіх авторів
//        List<Author> allAuthors = books.stream()
//                .flatMap(book -> book.getAuthors().stream())
//                .collect(Collectors.toList());
//
//        // Знаходимо книги з унікальними авторами
//        List<Book> booksWithUniqueAuthors = books.stream()
//                .filter(book -> book.getAuthors().stream().allMatch(author -> allAuthors.indexOf(author) == allAuthors.lastIndexOf(author)))
//                .collect(Collectors.toList());
//
//        booksWithUniqueAuthors.forEach(book -> System.out.println(book.getTitle()));


    }
}
