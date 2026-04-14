package org.learnStream;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsPractis {
    public static void main(String[] args) {

        System.out.println("------ 1 ----------");
//        Фільтрація та виведення елементів списку: Створіть список рядків.
//        Використовуйте Stream API для фільтрації та виведення всіх рядків,
//        які починаються на певну літеру (наприклад, "a").


        List<String> list = Arrays.asList("apple", "banana", "cherry", "avocado", "orange");

        list.stream()
                .filter(Objects::nonNull)
                .filter(a -> a.startsWith("a"))
                .forEach(System.out::println);


        System.out.println("------ 2 ----------");

//        Перетворення списку: Створіть список цілих чисел. Використовуйте Stream API та лямбда-вирази для створення нового списку, в якому всі числа будуть в квадраті.
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List collect = numbers.stream()
                .map(a -> a * a)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

        System.out.println("------ 3 ----------");

//        Підрахунок елементів, які відповідають певному критерію: Створіть список рядків. Використовуйте Stream API для підрахунку кількості рядків, довжина яких більше певного числа (наприклад, 5).
        List<String> list3 = Arrays.asList("apple", "banana", "cherry", "avocado", "orange");
//        List filteredList = list3.stream()
//                .filter(Objects::nonNull)
//                .filter(a -> a.length() > 5)
//                .collect(Collectors.toList());
//        System.out.println(filteredList.size());


        long numberOfElement = list3.stream()
                .filter(Objects::nonNull)
                .filter(a -> a.length() > 5)
                .count();
        System.out.println(numberOfElement);


        System.out.println("------ 4 ----------");
//        Пошук мінімального та максимального значення: Створіть список цілих чисел. Використовуйте Stream API для знаходження мінімального та максимального значення в списку.
        List<Integer> numbers4 = Arrays.asList(1);

        OptionalInt min = numbers4.stream().mapToInt(Integer::intValue).min();
        if (min.isPresent()) {
            System.out.println(min.getAsInt());
        } else {
            System.out.println("Value is empty");
        }

        System.out.println("------ 5 ----------");
//        Використання flatMap для перетворення списку списків в один список: Створіть список списків цілих чисел. Використовуйте flatMap для перетворення цього списку списків в один список.

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        List<Integer> list5 = listOfLists.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(list5);

        System.out.println("------ 6 ----------");
//        Використання flatMap для перетворення списку рядків в список символів: Створіть список рядків. Використовуйте flatMap для перетворення цього списку рядків в список символів.
        List<String> list6 = Arrays.asList("apple", "banana", "cherry");

        list6.stream().flatMap(a-> a.chars().mapToObj(c -> (char)c)).collect(Collectors.toList()).stream().forEach(System.out::print);
        System.out.println();


        System.out.println("------ 7 ----------");

//        Складне завдання - знаходження унікальних слів в тексті: Створіть рядок, який представляє собою текст. Використовуйте Stream API для знаходження всіх унікальних слів в тексті.

        String text = "Hello world! This is a test. Hello, hello, hello. Testing, testing, 1, 2, 3.";

        String[] words = text.toLowerCase().split("\\W+");
        for (String word:words) {
            System.out.println(word);

        }
        List<String> listWords = Arrays.stream(words).distinct().collect(Collectors.toList());
        System.out.println(listWords);


        System.out.println("------ 8 ----------");
//        Складне завдання - групування елементів за певним критерієм: Створіть список рядків.
//        Використовуйте Stream API для групування рядків за їх довжиною.
        List<String> list8 = Arrays.asList("apple", "banana", "cherry", "avocado", "orange");
        Map<Integer, List<String>> listOfGroups = list8.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(listOfGroups);




    }
}
