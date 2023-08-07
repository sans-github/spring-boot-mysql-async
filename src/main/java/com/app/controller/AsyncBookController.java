package com.app.controller;

import com.app.model.Book;
import com.app.service.AsyncBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/async")
public class AsyncBookController {
  @Autowired
  AsyncBookService bookService;
  
  @GetMapping("/books")
  @ResponseStatus(OK)
  public Flux<Book> getAllBooks(@RequestParam(required = false) String title) {
    if (title == null)
      return bookService.findAll();
    else
      return bookService.findByTitleContaining(title);
  }

  @GetMapping("/books/{id}")
  @ResponseStatus(OK)
  public Mono<Book> getBookById(@PathVariable("id") int id) {
    return bookService.findById(id);
  }

  @PostMapping("/books")
  @ResponseStatus(CREATED)
  public Mono<Book> createBook(@RequestBody Book book) {
    return bookService.save(new Book(book.getTitle(), book.getDescription(), false));
  }

  @PutMapping("/books/{id}")
  @ResponseStatus(OK)
  public Mono<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
    return bookService.update(id, book);
  }

  @DeleteMapping("/books/{id}")
  @ResponseStatus(NO_CONTENT)
  public Mono<Void> deleteBook(@PathVariable("id") int id) {
    return bookService.deleteById(id);
  }

  @DeleteMapping("/books")
  @ResponseStatus(NO_CONTENT)
  public Mono<Void> deleteAllBooks() {
    return bookService.deleteAll();
  }

  @GetMapping("/books/published")
  @ResponseStatus(OK)
  public Flux<Book> findByPublished() {
    return bookService.findByPublished(true);
  }
}
