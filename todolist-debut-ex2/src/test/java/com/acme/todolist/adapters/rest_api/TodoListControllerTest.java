package com.acme.todolist.adapters.rest_api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.Instant;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.acme.todolist.application.port.in.AddTodoItem;
import com.acme.todolist.application.port.in.GetTodoItems;
import com.acme.todolist.domain.TodoItem;

@WebMvcTest(controllers = TodoListController.class)
public class TodoListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetTodoItems getTodoItemsQuery;

    @MockBean
    private AddTodoItem addTodoItemUseCase;

    @Test
    public void testAjouterItem_Returns201Created() throws Exception {
        String jsonBody = "{\"id\":\"1\",\"time\":\"2026-03-12T10:30:00Z\",\"content\":\"Faire les courses\"}";

        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
               .andExpect(status().isCreated());

        verify(addTodoItemUseCase).addTodoItem(any(TodoItem.class));
    }

    @Test
    public void testGetAllTodoItems_Returns200AndList() throws Exception {
        TodoItem mockItem = new TodoItem("2", Instant.now(), "Acheter du pain");
        when(getTodoItemsQuery.getAllTodoItems()).thenReturn(Arrays.asList(mockItem));

        mockMvc.perform(get("/todos")
                .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray())
               .andExpect(jsonPath("$[0].content").value("Acheter du pain"));
    }
}
