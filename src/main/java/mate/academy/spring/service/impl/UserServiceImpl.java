package mate.academy.spring.service.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.spring.dao.UserDao;
import mate.academy.spring.entity.User;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }
}
