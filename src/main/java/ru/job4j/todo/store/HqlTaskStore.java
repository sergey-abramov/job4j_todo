package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlTaskStore implements TaskStore {
    private final CrudStore store;

    @Override
    public void create(Task task) {
        store.run(session -> session.persist(task));
    }

    @Override
    public boolean update(Task task) {
        var task1 = findById(task.getId());
        return store.booleanRun(session -> !task1.equals(session.merge(task)));
    }

    @Override
    public boolean delete(int id) {
       return store.booleanRun("delete from Task where id = :tId",
               Map.of("tId", id));
    }

    @Override
    public List<Task> findAll() {
        return store.query("from Task", Task.class);
    }

    @Override
    public Optional<Task> findById(int id) {
       return store.optional("from Task where id = :fId",
               Task.class, Map.of("fId", id));
    }

    @Override
    public List<Task> findByDone(boolean done) {
       return store.query("from Task where done = :fDone",
               Task.class, Map.of("fDone", done));
    }

    @Override
    public boolean updateDone(int id) {
        return store.booleanRun("update Task set done = :tDone where id = :tId",
                Map.of("tDone", true,
                        "tId", id));
    }
}
