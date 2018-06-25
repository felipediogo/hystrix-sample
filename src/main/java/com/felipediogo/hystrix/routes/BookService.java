package com.felipediogo.hystrix.routes;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {

    public List<Book> getBooks() {
        return new GetBooksCommand().execute();
    }

}
