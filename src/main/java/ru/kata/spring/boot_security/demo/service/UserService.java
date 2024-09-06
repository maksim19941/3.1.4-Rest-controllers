package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    List<User> getListUser();

    @Transactional
    void save(User newUser);

    @Transactional
    void update(User updateUser);

    @Transactional
    void delete(long id);

    User getUser(long id);

    User findUserByUsername(String name);

    Set<Role> getUserRole(long id);

    Optional<User> findUserByUsernameValidate(String name);

}
