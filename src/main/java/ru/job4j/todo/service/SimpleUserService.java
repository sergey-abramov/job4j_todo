package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.UserStore;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {

    private final UserStore store;

    @Override
    public Optional<User> save(User user) {
        return store.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String login, String password) {
        return store.findByEmailAndPassword(login, password);
    }
}
