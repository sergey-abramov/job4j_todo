package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriorityStore {

    private final CrudStore store;

    public Optional<Priority> findById(int id) {
        return store.optional("from Priority where id = :pId", Priority.class, Map.of("pId", id));
    }

    public List<Priority> findAll() {
        return store.query("from Priority", Priority.class);
    }
}
