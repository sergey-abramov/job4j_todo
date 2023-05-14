package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlUserStore implements UserStore {

    private final SessionFactory sf;

    @Override
    public boolean save(User user) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            rsl = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String login, String password) {
        Session session = sf.openSession();
        Optional<User> rsl = Optional.empty();
        try {
            session.beginTransaction();
            rsl = session
                    .createQuery("""
                                    from User as u
                                    where u.login = :fLogin
                                    and u.password = :fPassword
                                    """,
                            User.class)
                    .setParameter("fLogin", login)
                    .setParameter("fPassword", password)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }
}
