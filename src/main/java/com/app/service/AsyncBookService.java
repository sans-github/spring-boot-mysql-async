package com.app.service;

import com.app.model.Book;
import com.app.repository.AsyncBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class AsyncBookService {

  @Autowired
  AsyncBookRepository asyncBookRepository;

  public Flux<Book> findAll() {
    return asyncBookRepository.findAll();
  }

  public Flux<Book> findByTitleContaining(String title) {
    return asyncBookRepository.findByTitleContaining(title);
  }

  public Mono<Book> findById(int id) {
    return asyncBookRepository.findById(id);
  }

  public Mono<Book> save(Book book) {
    return asyncBookRepository.save(book);
  }

  public Mono<Book> update(int id, Book book) {
    return asyncBookRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
        .flatMap(optionalBook -> {
          if (optionalBook.isPresent()) {
            book.setId(id);
            return asyncBookRepository.save(book);
          }

          return Mono.empty();
        });
  }

  public Mono<Void> deleteById(int id) {
    return asyncBookRepository.deleteById(id);
  }

  public Mono<Void> deleteAll() {
    return asyncBookRepository.deleteAll();
  }

  public Flux<Book> findByPublished(boolean isPublished) {
    return asyncBookRepository.findByPublished(isPublished);
  }
}
