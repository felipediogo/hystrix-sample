package com.felipediogo.hystrix.commands;

import com.felipediogo.hystrix.repository.Book;
import com.felipediogo.hystrix.repository.BookRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

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

    public void postBook(Book book) {
        bookRepository.save(book);
    }

    public void postBookFallback(Book book) {
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
}
