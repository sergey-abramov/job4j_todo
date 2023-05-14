package ru.job4j.todo.store;

import ru.job4j.todo.model.User;

import java.util.Optional;

public interface UserStore {

    boolean save(User user);

    Optional<User> findByEmailAndPassword(String login, String password);
}
