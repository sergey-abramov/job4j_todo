package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CategoryStore {

    private final CrudStore store;

    public List<Category> findByListId(List<Integer> id) {
        return store.query("from Category where id in :cId", Category.class, Map.of("cId", id));
    }

    public List<Category> findAll() {
        return store.query("from Category", Category.class);
    }
}
