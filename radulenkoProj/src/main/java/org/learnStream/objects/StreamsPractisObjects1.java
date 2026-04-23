package org.learnStream.objects;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsPractisObjects1 {
    public static void main(String[] args) {


        Author author1 = new Author("Alice", "Smith", 30);
        Author author2 = new Author("Bob", "Johnson", 40);
        Author author3 = new Author("Charlie", "Williams", 50);

        Book book1 = new Book("Book1", 150, 19.99, Arrays.asList(author1, author2));
        Book book2 = new Book("Book2", 50, 9.99, Arrays.asList(author1));
        Book book3 = new Book("Book3", 200, 29.99, Arrays.asList(author2, author3));

        List<Book> books = Arrays.asList(book1, book2, book3);


        System.out.println("------ 1 ----------");
//        Наприклад, ми можемо згрупувати книги за кількістю авторів,
//        а потім підрахувати загальну кількість сторінок для кожної групи.
//
//        Map<Integer, Integer> pagesByAuthor = books.stream()
//                .collect(Collectors.groupingBy(
//                        book -> book.getAuthors().size(),
//                        Collectors.summingInt(Book::getNumberOfPages)))
//        ;

        Map<Integer, Integer> pagesByAuthor = books.stream()
                        .collect(Collectors.groupingBy(book -> book.getAuthors().size(), Collectors.summingInt(Book::getNumberOfPages)));
        System.out.println(pagesByAuthor);



    }
}
