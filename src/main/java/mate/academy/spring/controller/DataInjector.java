package mate.academy.spring.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;

import mate.academy.spring.entity.Author;
import mate.academy.spring.entity.Book;
import mate.academy.spring.entity.Role;
import mate.academy.spring.entity.User;
import mate.academy.spring.service.AuthorService;
import mate.academy.spring.service.BookService;
import mate.academy.spring.service.RoleService;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInjector {
    private static final Role USER = new Role("ROLE_USER");
    private static final Role ADMIN = new Role("ROLE_ADMIN");
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void injectData() {
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

        bookService.add(goblinReservation);
        bookService.add(city);
        bookService.add(aliceInWonderland);
        bookService.add(foundation);

        authorService.add(simak);
        authorService.add(asimov);
        authorService.add(carroll);

        roleService.add(USER);
        roleService.add(ADMIN);

        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin.admin@example.com");
        admin.setUsername("admin");
        admin.setPassword("1");
        Role adminRoleUser = roleService.getRoleByName("ROLE_USER").get();
        Role adminRoleAdmin = roleService.getRoleByName("ROLE_ADMIN").get();
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRoleUser);
        adminRoles.add(adminRoleAdmin);
        admin.setRoles(adminRoles);
        userService.add(admin);

        User david = new User();
        david.setFirstName("David");
        david.setLastName("Miller");
        david.setEmail("david.miller@example.com");
        david.setUsername("david");
        david.setPassword("1");
        Role davidRole  = roleService.getRoleByName("ROLE_USER").get();
        Set<Role> davidRoles = new HashSet<>();
        davidRoles.add(davidRole);
        david.setRoles(davidRoles);
        userService.add(david);

        User sameer = new User();
        sameer.setFirstName("Sameer");
        sameer.setLastName("Singh");
        sameer.setEmail("sameer.singh@example.com");
        sameer.setUsername("sameer");
        sameer.setPassword("1");
        Role sameerRole  = roleService.getRoleByName("ROLE_USER").get();
        Set<Role> sameerRoles = new HashSet<>();
        sameerRoles.add(sameerRole);
        sameer.setRoles(sameerRoles);
        userService.add(sameer);

        User paul = new User();
        paul.setFirstName("Paul");
        paul.setLastName("Smith");
        paul.setEmail("paul.smith@example.com");
        paul.setUsername("paul");
        paul.setPassword("1");
        Role paulRole  = roleService.getRoleByName("ROLE_USER").get();
        Set<Role> paulRoles = new HashSet<>();
        paulRoles.add(paulRole);
        paul.setRoles(paulRoles);
        userService.add(paul);
    }
}
