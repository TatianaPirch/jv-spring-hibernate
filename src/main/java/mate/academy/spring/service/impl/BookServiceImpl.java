package mate.academy.spring.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.spring.dao.BookDao;
import mate.academy.spring.entity.Book;
import mate.academy.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Transactional
    @Override
    public void add(Book book) {
        bookDao.add(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> listBooks() {
        return bookDao.listBooks();
    }

    @Transactional
    @Override
    public List<Book> findByTitle(String title) {
        return bookDao.findByTitle(title);
    }

    @Transactional
    @Override
    public Book findById(Long id) {
        try {
            return bookDao.findById(id).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            System.out.println("Not found book with id = " + id);
        }
        return null;
    }
}
