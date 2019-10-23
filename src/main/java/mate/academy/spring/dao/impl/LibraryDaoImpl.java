package mate.academy.spring.dao.impl;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.TypedQuery;

import mate.academy.spring.dao.LibraryDao;
import mate.academy.spring.entity.Book;
import mate.academy.spring.entity.Rent;
import mate.academy.spring.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryDaoImpl implements LibraryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Rent rentBook(User user, Book book) {
        Rent rent = new Rent(LocalDate.now(),user, book, true);
        sessionFactory.getCurrentSession().save(rent);
        return rent;
    }

    @Override
    public Rent returnBook(User user, Book book) {
        TypedQuery<Rent> query = sessionFactory.getCurrentSession()
                .createQuery("FROM Rent WHERE user=:user AND book=:book"
                                + " AND is_active =:active", Rent.class).setMaxResults(1);
        query.setParameter("user", user);
        query.setParameter("book", book);
        query.setParameter("active", true);
        Rent rent = query.getSingleResult();
        rent.setActive(false);
        sessionFactory.getCurrentSession().update(rent);
        return rent;
    }

    @Override
    public List<Book> getBooksRentByUser(User user) {
        TypedQuery<Book> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT book FROM Rent WHERE user_id =:user_id"
                        + " AND is_active =:active", Book.class);
        query.setParameter("user_id", user.getId());
        query.setParameter("active", true);
        return query.getResultList();
    }
}
