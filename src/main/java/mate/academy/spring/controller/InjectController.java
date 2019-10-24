package mate.academy.spring.controller;

import java.util.ArrayList;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inject")
public class InjectController {

    @GetMapping
    public String injectData() {
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
        Rent rent = libraryService.rentBook(sameer, city);
        return "forward:";
    }
}
