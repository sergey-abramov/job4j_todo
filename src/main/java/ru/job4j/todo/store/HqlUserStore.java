package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlUserStore implements UserStore {

    private final CrudStore store;

    @Override
    public boolean save(User user) {
       store.run(session -> session.persist(user));
       return findByEmailAndPassword(user.getLogin(), user.getPassword()).isPresent();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String login, String password) {
       return store.optional("from User where login = :uLogin and password = :uPassword",
               User.class,
               Map.of(
                       "uLogin", login,
                       "uPassword", password));
    }
}
