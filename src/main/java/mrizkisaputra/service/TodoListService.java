package mrizkisaputra.service;

import mrizkisaputra.entity.TodoList;

public interface TodoListService {
    void insert(TodoList todoList);
    void displayTodolist();

    default void displayTodoById(Integer todoId) { }
    Boolean update(Integer todoId, String newTodo);
    Boolean delete(Integer todoId);
}
