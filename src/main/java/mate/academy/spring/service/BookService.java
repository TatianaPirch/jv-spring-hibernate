package mate.academy.spring.service;

import java.util.List;
import java.util.Optional;

import mate.academy.spring.entity.Book;

public interface BookService {

    void add(Book book);

    List<Book> listBooks();

    List<Book> findByTitle(String title);

    Optional<Book> findById(Long id);
}
