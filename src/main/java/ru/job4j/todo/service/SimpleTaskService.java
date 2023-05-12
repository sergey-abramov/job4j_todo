package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.TaskStore;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {

    private final TaskStore store;

    @Override
    public void create(Task task) {
        store.create(task);
    }

    @Override
    public boolean update(Task task) {
        return store.update(task);
    }

    @Override
    public boolean delete(int id) {
        return store.delete(id);
    }

    @Override
    public List<Task> findAll() {
        return store.findAll();
    }

    @Override
    public Optional<Task> findById(int id) {
        return store.findById(id);
    }

    @Override
    public List<Task> findByDone(boolean done) {
        return store.findByDone(done);
    }

    @Override
    public boolean updateDone(int id) {
        return store.updateDone(id);
    }
}
