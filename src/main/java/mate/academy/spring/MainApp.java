package mate.academy.spring;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mate.academy.spring.config.AppConfig;
import mate.academy.spring.entity.Author;
import mate.academy.spring.entity.Book;
import mate.academy.spring.entity.Rent;
import mate.academy.spring.entity.User;
import mate.academy.spring.service.AuthorService;
import mate.academy.spring.service.BookService;
import mate.academy.spring.service.LibraryService;
import mate.academy.spring.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        BookService bookService = context.getBean(BookService.class);
        AuthorService authorService = context.getBean(AuthorService.class);
        LibraryService libraryService = context.getBean(LibraryService.class);

        User sunil = new User("Sunil", "Bora", "suni.bora@example.com");
        User david = new User("David", "Miller", "david.miller@example.com");
        User sameer = new User("Sameer", "Singh", "sameer.singh@example.com");
        User paul = new User("Paul", "Smith", "paul.smith@example.com");

        Book goblinReservation = new Book("The Goblin Reservation", 1968, 100., new ArrayList<>());
        Book city = new Book("City", 1952, 200., new ArrayList<>());
        Book aliceInWonderland = new Book("Alice in Wonderland", 1865, 150., new ArrayList<>());
        Book foundation = new Book("Foundation", 1942, 250., new ArrayList<>());

        Author simak = new Author("Clifford", "Simak", new ArrayList<>());
        Author asimov = new Author("Isaac", "Asimov", new ArrayList<>());
        Author carroll = new Author("Lewis", "Carroll", new ArrayList<>());

        simak.getBooks().add(goblinReservation);
        simak.getBooks().add(city);
        carroll.getBooks().add(aliceInWonderland);
        asimov.getBooks().add(foundation);

        // Add Users
        userService.add(sunil);
        userService.add(david);
        userService.add(sameer);
        userService.add(paul);

        // Add Books
        bookService.add(goblinReservation);
        bookService.add(city);
        bookService.add(aliceInWonderland);
        bookService.add(foundation);

        // Add authors
        authorService.add(simak);
        authorService.add(asimov);
        authorService.add(carroll);

        // Get Users
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("User id = " + user.getId());
            System.out.println("User first name = " + user.getFirstName());
            System.out.println("User last name = " + user.getLastName());
            System.out.println("User email = " + user.getEmail());
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

        // Get authors by name
        List<Author> authors = authorService.findByName("Clifford");
        for (Author author : authors) {
            System.out.println("Author id = " + author.getId());
            System.out.println("Author name = " + author.getName());
            System.out.println("Author surname = " + author.getSurname());
            System.out.println();
            System.out.println("Book by " + author.getSurname() + " author:");
            System.out.println();
            for (Book book: author.getBooks()) {
                System.out.println("Book id = " + book.getId());
                System.out.println("Book title = " + book.getTitle());
                System.out.println("Book year = " + book.getYear());
                System.out.println("Book price = " + book.getPrice());
                System.out.println();
            }
            System.out.println();
        }

        //Rent book
        Rent rent = libraryService.rentBook(sameer, city);
        System.out.println("Rent id = " + rent.getId());
        System.out.println("Rent date = " + rent.getRentDate());
        System.out.println("Book title = " + rent.getBook().getTitle());
        System.out.println("User suname = " + rent.getUser().getLastName());
        System.out.println();

        //List of rented books by user
        List<Book> listRent = libraryService.getBooksRentByUser(sameer);
        for (Book book : listRent) {
            System.out.println("Book id = " + book.getId());
            System.out.println("Book title = " + book.getTitle());
            System.out.println();
        }
        System.out.println();

        context.close();
    }
}
