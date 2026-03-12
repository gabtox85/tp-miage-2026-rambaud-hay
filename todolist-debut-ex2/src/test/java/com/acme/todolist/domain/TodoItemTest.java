package com.acme.todolist.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

public class TodoItemTest {

    @Test
    public void testFinalContent_WhenNotLate() {
        TodoItem item = new TodoItem("1", Instant.now().minus(5, ChronoUnit.HOURS), "Faire les courses");
        assertEquals("Faire les courses", item.finalContent());
    }

    @Test
    public void testFinalContent_WhenLate() {
        TodoItem item = new TodoItem("2", Instant.now().minus(2, ChronoUnit.DAYS), "Payer les impôts");
        assertEquals("[LATE!] Payer les impôts", item.finalContent());
    }
}
