package mate.academy.spring.dao;

import java.util.List;

import mate.academy.spring.entity.Author;

public interface AuthorDao {

    void add(Author author);

    List<Author> listAuthors();

    List<Author> findByName(String name);

    List<Author> findByNameAndSurname(String name, String surname);
}
