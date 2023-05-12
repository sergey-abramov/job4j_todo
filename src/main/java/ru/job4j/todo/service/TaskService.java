package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    void create(Task task);

    boolean update(Task task);

    boolean updateDone(int id);

    boolean delete(int id);

    List<Task> findAll();

    Optional<Task> findById(int id);

    List<Task> findByDone(boolean done);
}
