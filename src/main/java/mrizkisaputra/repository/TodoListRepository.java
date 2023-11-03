package mrizkisaputra.repository;

import mrizkisaputra.entity.TodoList;

import java.util.ArrayList;

public interface TodoListRepository {
    void insert(TodoList todoList);

    ArrayList<TodoList> getAll();

    default TodoList findByTodoId(Integer todoId) {
        return null;
    }
    Boolean update(Integer todoId, String newTodo);
    Boolean delete(Integer todoId);

}
