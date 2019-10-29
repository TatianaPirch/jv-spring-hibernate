package mate.academy.spring.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.spring.entity.User;

public interface UserDao {

    User add(User user);

    List<User> listUsers();

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);
}
