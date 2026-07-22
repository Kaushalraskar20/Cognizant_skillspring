package com.library.repository;

/**
 * BookRepository - Data access layer for Book operations.
 *
 * In a real application this would connect to a database.
 * For this exercise it simulates data access via console output.
 *
 * Exercise 1: This bean is defined in applicationContext.xml and managed
 *             by the Spring IoC container.
 * Exercise 2: This class is injected into BookService via setter injection
 *             (Dependency Injection), wired through applicationContext.xml.
 */
public class BookRepository {

    public void saveBook(String title) {
        System.out.println("[BookRepository] Saving book: " + title);
    }

    public String findBookByTitle(String title) {
        System.out.println("[BookRepository] Fetching book: " + title);
        return "Book{title='" + title + "', author='Unknown', available=true}";
    }

    public void deleteBook(String title) {
        System.out.println("[BookRepository] Deleting book: " + title);
    }
}
