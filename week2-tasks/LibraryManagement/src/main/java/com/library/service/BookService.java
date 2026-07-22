package com.library.service;

import com.library.repository.BookRepository;

/**
 * BookService - Business logic layer for library operations.
 *
 * Exercise 1: Bean defined in applicationContext.xml.
 *
 * Exercise 2: Dependency Injection (DI) via setter method.
 *   Spring reads applicationContext.xml and calls setBookRepository()
 *   automatically, injecting the BookRepository bean.
 *   This is the Inversion of Control (IoC) principle: the framework,
 *   not your code, controls object creation and wiring.
 *
 * Exercise 7 (constructor injection variant): also demonstrated below.
 *   Constructor injection ensures BookRepository is ALWAYS available
 *   the moment BookService is created (no null-check needed).
 *   Setter injection allows the dependency to be changed after creation
 *   (useful for optional dependencies or re-configuration).
 */
public class BookService {

    private BookRepository bookRepository;

    // ----------------------------------------------------------------
    // Constructor injection (Exercise 7)
    // Spring calls this constructor if <constructor-arg> is used in XML.
    // ----------------------------------------------------------------
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] Created via constructor injection.");
    }

    // ----------------------------------------------------------------
    // Default constructor required when Spring uses setter injection.
    // ----------------------------------------------------------------
    public BookService() {
        System.out.println("[BookService] Created via default constructor.");
    }

    // ----------------------------------------------------------------
    // Setter injection (Exercise 2 & 7)
    // Spring calls this after creating the bean if <property> is used.
    // ----------------------------------------------------------------
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] BookRepository injected via setter.");
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    // ----------------------------------------------------------------
    // Business methods
    // ----------------------------------------------------------------

    public void addBook(String title) {
        System.out.println("[BookService] Adding book: " + title);
        bookRepository.saveBook(title);
    }

    public String getBook(String title) {
        System.out.println("[BookService] Getting book: " + title);
        return bookRepository.findBookByTitle(title);
    }

    public void removeBook(String title) {
        System.out.println("[BookService] Removing book: " + title);
        bookRepository.deleteBook(title);
    }
}
