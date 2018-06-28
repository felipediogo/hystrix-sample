package com.felipediogo.hystrix.commands;

import com.felipediogo.hystrix.repository.Book;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class GetBooksCommand extends HystrixCommand<List<Book>> {

    GetBooksCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("com.felipediogo.hystrix"));
    }

    @Override
    protected List<Book> run() throws Exception {
        int answer = new Random().nextInt(2) + 1;
        if (answer % 2 == 0) {
            throw new Exception();
        } else {
            return Collections.singletonList(buildBook());
        }
    }

    @Override
    protected List<Book> getFallback() {
        return Collections.singletonList(buildFallbackBooks());
    }

    private Book buildBook() {
        Book book = new Book();
        book.setId(1);
        book.setAuthor("George R. R. Martin");
        book.setTitle("Game of Thrones");
        book.setPublisher("Editora Teste");
        book.setValue(59.99);
        return book;
    }

    private Book buildFallbackBooks() {
        Book book = new Book();
        book.setId(2);
        book.setAuthor("DEFAULT");
        book.setTitle("DEFAULT");
        book.setPublisher("DEFAULT");
        book.setValue(59.99);
        return book;
    }
}
