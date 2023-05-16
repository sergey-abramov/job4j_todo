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
    public Optional<User> save(User user) {
        Optional<User> rsl;
        try {
            store.run(session -> session.persist(user));
            rsl = Optional.of(user);
        } catch (Exception e) {
            rsl = Optional.empty();
        }
        return rsl;
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
