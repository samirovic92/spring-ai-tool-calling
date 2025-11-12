package com.sei.toolcalling.tools;

import com.sei.toolcalling.domain.model.Todo;
import com.sei.toolcalling.representation.request.CreateTodoRequest;
import com.sei.toolcalling.representation.response.TodoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.web.client.RestTemplate;

public class TodoTools {

    private final static Logger log = LoggerFactory.getLogger(TodoTools.class);
    private final String baseUrl = "http://localhost:8080";
    private final RestTemplate restTemplate;

    public TodoTools() {
        this.restTemplate = new RestTemplate();
    }

    @Tool(description = "Get a todo by ID")
    public TodoResponse findTodoById(Long id) {
        log.info("Getting todo with id {}", id);
        var url = String.format("%s/todos/%s", baseUrl, id);
        return restTemplate.getForObject(url, TodoResponse.class);
    }

    @Tool(description = "Create a new todo")
    public TodoResponse createTodo(CreateTodoRequest todo) {
        log.info("Creating todo {}", todo);
        var url = String.format("%s/todos", baseUrl);
        return restTemplate.postForObject(url, todo, TodoResponse.class);
    }

    @Tool
    public TodoResponse[] findAllTodos() {
        log.info("Getting all todos");
        var url = String.format("%s/todos", baseUrl);
        return restTemplate.getForObject(url, TodoResponse[].class);
    }

    @Tool(description = "Delete a todo by ID")
    public void deleteTodo(Long id) {
        log.info("Deleting todo with id {}", id);
        var url = String.format("%s/todos/%s", baseUrl, id);
        restTemplate.delete(url);
    }
}
