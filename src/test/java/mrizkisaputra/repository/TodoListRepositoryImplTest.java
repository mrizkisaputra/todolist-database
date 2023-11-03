package mrizkisaputra.repository;

import mrizkisaputra.entity.TodoList;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodoListRepositoryImplTest {
    private final TodoListRepository repository = new TodoListRepositoryImpl();

    @Test
    @Order(1)
    @DisplayName("[TEST insert] sebaiknya insert data berhasil")
    public void insertSuccess() {
        TodoList todo1 = new TodoList("Java Basic");
        TodoList todo2 = new TodoList("Java OOP");
        TodoList todo3 = new TodoList("Java Database");

        repository.insert(todo1);
        repository.insert(todo2);
        repository.insert(todo3);
    }

    @Test
    @Order(2)
    @Disabled
    @DisplayName("[TEST getAll] sebaiknya mengembalikan data todolist saat tidak kosong")
    public void getAllTodoIsNotNull() {
        ArrayList<TodoList> listTodo = repository.getAll();
        assertNotNull(listTodo);
    }

    @Test
    @Order(3)
    @DisplayName("[TEST 1 delete] sebaiknya mengembalikan true")
    public void deleteCase1() {
        Boolean result = repository.delete(4);
        Assertions.assertTrue(result);
    }

    @Test
    @Order(4)
    @DisplayName("[TEST 2 delete] sebaiknya mengembalikan false")
    public void deleteCase2() {
        Boolean result = repository.delete(2);
        Assertions.assertFalse(result);
    }

    @Test
    @Order(5)
    @DisplayName("[TEST 1 update] sebaiknya mengembalikan true")
    public void updateCase1() {
        Boolean result = repository.update(5, "New Todo Test");
        Assertions.assertTrue(result);
    }

    @Test
    @Order(6)
    @DisplayName("[TEST 2 update] sebaiknya mengembalikan false")
    public void updateCase2() {
        Boolean result = repository.update(15, "New Todo Test");
        Assertions.assertFalse(result);
    }


}
