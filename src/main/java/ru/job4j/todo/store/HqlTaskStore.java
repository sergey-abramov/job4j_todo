package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlTaskStore implements TaskStore {

    private final SessionFactory sf;

    @Override
    public void create(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Task task) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            session.update(task);
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
    public boolean delete(int id) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            rsl = session.createQuery("delete Task where id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public List<Task> findAll() {
        Session session = sf.openSession();
        List<Task> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            rsl = session.createQuery("from Task", Task.class)
                            .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        Optional<Task> rsl = Optional.empty();
        try {
            session.beginTransaction();
            rsl = session.createQuery("from Task where id = :fId", Task.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public List<Task> findByDone(boolean done) {
        Session session = sf.openSession();
       List<Task> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            rsl = session.createQuery("from Task where done = :fDone", Task.class)
                    .setParameter("fDone", done)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public boolean updateDone(int id) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            rsl = session.createQuery("update Task set done = :fDone where id = :fId")
                    .setParameter("fDone", true)
                    .setParameter("fId", id)
                    .executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }
}
