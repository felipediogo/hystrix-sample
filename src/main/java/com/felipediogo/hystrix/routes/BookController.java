package com.felipediogo.hystrix.routes;

import com.felipediogo.hystrix.repository.Book;
import com.felipediogo.hystrix.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("data/v1/")
public class BookController {

    private BookRepository repository;
    private GetBooksCommand booksCommand;

    public BookController(BookRepository repository,
                          GetBooksCommand booksCommand) {
        this.repository = repository;
        this.booksCommand = booksCommand;
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Book> getBooks(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(new GetBooksCommand().execute().get(0));
//        return repository
//            .findById(id)
//            .map(ResponseEntity::ok)
//            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/books")
    public ResponseEntity<Void> postBooks(@RequestBody Book book) {
        repository.save(book);
        return ResponseEntity.ok().build();
    }


}
