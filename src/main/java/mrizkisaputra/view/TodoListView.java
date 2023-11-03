package mrizkisaputra.view;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import mrizkisaputra.entity.TodoList;
import mrizkisaputra.repository.TodoListRepository;
import mrizkisaputra.service.TodoListService;
import mrizkisaputra.utils.TextInput;

@RequiredArgsConstructor
public class TodoListView {
    @NonNull
    private TodoListRepository repository;

    @NonNull
    private TodoListService service;

    public void mainActivity() {
        while (true) {
            System.out.println("\n\n=========================TODO LIST=========================");
            service.displayTodolist();
            System.out.println("===========================================================");
            System.out.println("==========================+MENU+===========================");
            System.out.println("" +
                    "[1]. add\n" +
                    "[2]. delete\n" +
                    "[3]. update\n" +
                    "[x]. exit");
            System.out.println("===========================================================");
            System.out.print("select one => ");
            val position = TextInput.getTextInput();
            switch (position) {
                case "1": addTodo(); break;
                case "2": delete(); break;
                case "3": update(); break;
                case "x", "X": System.exit(0); break;
                default: System.err.println(position+" not found");
            }
        }
    }

    private void addTodo() {
        System.out.print("enter todo:: ");
        String todo = TextInput.getTextInput();
        service.insert(new TodoList(todo));
    }

    private void update() {
        System.out.print("enter number you want update:: ");
        String todoId = TextInput.getTextInput();
        System.out.print("enter new todo:: ");
        String newTodo = TextInput.getTextInput();
        boolean result = service.update(Integer.valueOf(todoId), newTodo);
        val message = (result) ? "update success" : "update failed";
        System.out.println(message);
    }

    private void delete() {
        System.out.print("enter number you want delete:: ");
        String todoId = TextInput.getTextInput();
        boolean result = service.delete(Integer.valueOf(todoId));
        val message = (result) ? "delete success" : "delete failed";
        System.out.println(message);
    }
}
