package mrizkisaputra.service;

import lombok.val;
import mrizkisaputra.entity.TodoList;
import mrizkisaputra.repository.TodoListRepository;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class TodoListServiceImpl implements TodoListService {
    private final TodoListRepository repository;

    public TodoListServiceImpl(TodoListRepository todoListRepository) {
        this.repository = todoListRepository;
    }
    @Override
    public void insert(TodoList todoList) {
        repository.insert(todoList);
    }

    @Override
    public void displayTodolist() {
        ArrayList<TodoList> listTodo = repository.getAll();
        for (TodoList todo : listTodo) {
            String formatted = todo.getCreatedAt().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
            val value = String.format("%d. %s -created at %s",
                    todo.getTodoId(),
                    todo.getTodo(),
                    formatted);
            System.out.println(value);
        }
    }

    @Override
    public Boolean update(Integer todoId, String newTodo) {
        return repository.update(todoId, newTodo);
    }

    @Override
    public Boolean delete(Integer todoId) {
        return repository.delete(todoId);
    }
}
