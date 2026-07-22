package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * LibraryManagementApplication - Entry point for the Library Management System.
 *
 * Exercise 1: Loads the Spring ApplicationContext from applicationContext.xml
 *             and retrieves the BookService bean to verify configuration.
 *
 * Exercise 2: Demonstrates that BookRepository is successfully injected into
 *             BookService by Spring's IoC container (DI in action).
 *
 * Exercise 4: This project is structured as a Maven project with Spring
 *             dependencies defined in pom.xml and the Maven Compiler Plugin
 *             configured for Java 1.8. Run with: mvn compile exec:java
 */
public class LibraryManagementApplication {

    public static void main(String[] args) {
        System.out.println("=== Library Management System Starting ===\n");

        // Exercise 1: Load the Spring Application Context from XML config file.
        // ClassPathXmlApplicationContext scans the classpath for applicationContext.xml.
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("\n--- Spring context loaded successfully ---\n");

        // Exercise 1 & 2: Retrieve BookService bean from the container.
        // Spring has already constructed BookService AND injected BookRepository
        // into it via the setter defined in applicationContext.xml.
        BookService bookService = context.getBean("bookService", BookService.class);

        // Exercise 2: Verify DI worked - BookRepository should NOT be null.
        System.out.println("\n[Verification] BookRepository injected: "
                + (bookService.getBookRepository() != null ? "YES ✓" : "NO ✗"));

        System.out.println("\n--- Testing BookService operations ---");
        // AOP LoggingAspect will fire around each of these calls.
        bookService.addBook("Clean Code");
        System.out.println();
        bookService.addBook("The Pragmatic Programmer");
        System.out.println();

        String found = bookService.getBook("Clean Code");
        System.out.println("[Result] " + found);
        System.out.println();

        bookService.removeBook("Clean Code");

        System.out.println("\n=== Library Management System Finished ===");

        // Close the context to release resources.
        ((ClassPathXmlApplicationContext) context).close();
    }
}
