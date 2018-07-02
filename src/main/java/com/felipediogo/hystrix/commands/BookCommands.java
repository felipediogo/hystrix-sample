package com.felipediogo.hystrix.commands;

import com.felipediogo.hystrix.repository.Book;
import com.felipediogo.hystrix.repository.BookRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;

@Component
public class BookCommands {

    private final BookRepository bookRepository;

    public BookCommands(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @HystrixCommand(
        fallbackMethod = "getFallbackBook",
        groupKey = "getBook"
    )
    public Optional<Book> getBook(Integer id) {
        return bookRepository
            .findById(id);
    }

    @HystrixCommand(
        fallbackMethod = "getFallbackBooks",
        groupKey = "getAllBooks"
    )
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    public void postBook(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> getFallbackBook(Integer id) {
        Book book = new Book();
        book.setId(2);
        book.setAuthor("DEFAULT");
        book.setTitle("DEFAULT");
        book.setPublisher("DEFAULT");
        book.setValue(59.99);
        return of(book);
    }

    public List<Book> getFallbackBooks() {
        return Collections.emptyList();
    }
}
