package com.acme.todolist.adapters.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TodoItemPersistenceAdapterTest {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Test
    public void testSaveAndRetrieveTodoItemJpaEntity() {
        TodoItemJpaEntity entity = new TodoItemJpaEntity("123", Instant.now(), "Tondre la pelouse", true);
        
        todoItemRepository.save(entity);

        List<TodoItemJpaEntity> entities = todoItemRepository.findAll();

        assertThat(entities).hasSize(1);
        assertThat(entities.get(0).getContent()).isEqualTo("Tondre la pelouse");
        assertThat(entities.get(0).getId()).isEqualTo("123");
    }
}
