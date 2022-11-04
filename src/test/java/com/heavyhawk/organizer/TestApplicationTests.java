package com.heavyhawk.organizer;

import com.heavyhawk.organizer.models.Todo;
import com.heavyhawk.organizer.repositories.TodoRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationTests {

    @Autowired
    private TodoRepository todoRepository;

    private static final String todoName = "Test Todo 1";

    @BeforeEach
    public void addTodosToDb() {
        Todo todo = new Todo();
        todo.setName(todoName);

        todoRepository.save(todo);
    }

    @AfterEach
    public void clearTodoInDb() {
        todoRepository.deleteAll();
    }

    @Test
    void testGetRequest() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<Todo> todoResponseEntity =
                restTemplate.getForEntity("http://localhost:8080/api/todos/1", Todo.class);
        Assert.assertEquals(todoResponseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(todoResponseEntity.getBody());
        Assert.assertEquals(todoResponseEntity.getBody().getName(), todoName);
    }

}
