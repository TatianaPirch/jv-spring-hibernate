package mate.academy.spring;

import java.sql.SQLException;
import java.util.List;

import mate.academy.spring.config.AppConfig;
import mate.academy.spring.entity.Book;
import mate.academy.spring.entity.User;
import mate.academy.spring.service.BookService;
import mate.academy.spring.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        BookService bookService = context.getBean(BookService.class);

        // Add Users
        userService.add(new User("Sunil", "Bora", "suni.bora@example.com"));
        userService.add(new User("David", "Miller", "david.miller@example.com"));
        userService.add(new User("Sameer", "Singh", "sameer.singh@example.com"));
        userService.add(new User("Paul", "Smith", "paul.smith@example.com"));

        // Add Books
        bookService.add(new Book("The Goblin Reservation", 1968, 100.));
        bookService.add(new Book("City", 1952, 200.));
        bookService.add(new Book("Alice in Wonderland", 1865, 150.));
        bookService.add(new Book("Foundation", 1942, 250.));

        // Get Users
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        // Get Books
        List<Book> books = bookService.listBooks();
        for (Book book : books) {
            System.out.println("Book id = " + book.getId());
            System.out.println("Book title = " + book.getTitle());
            System.out.println("Book year = " + book.getYear());
            System.out.println("Book price = " + book.getPrice());
            System.out.println();
        }

        context.close();
    }
}
