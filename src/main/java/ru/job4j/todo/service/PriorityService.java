package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.store.PriorityStore;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriorityService {

    private final PriorityStore store;

    public Optional<Priority> findById(int id) {
        return store.findById(id);
    }

    public List<Priority> findAll() {
        return store.findAll();
    }
}
