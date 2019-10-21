package mate.academy.spring.service.impl;

import java.util.List;

import mate.academy.spring.dao.LibraryDao;
import mate.academy.spring.entity.Book;
import mate.academy.spring.entity.Rent;
import mate.academy.spring.entity.User;
import mate.academy.spring.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryDao libraryDao;

    @Transactional
    @Override
    public Rent rentBook(User user, Book book) {
        return libraryDao.rentBook(user, book);
    }

    @Transactional
    @Override
    public Rent returnBook(User user, Book book) {
        return libraryDao.returnBook(user, book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooksRentByUser(User user) {
        return libraryDao.getBooksRentByUser(user);
    }
}
