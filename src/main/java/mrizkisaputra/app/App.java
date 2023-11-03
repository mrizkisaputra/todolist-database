package mrizkisaputra.app;

import mrizkisaputra.repository.TodoListRepository;
import mrizkisaputra.repository.TodoListRepositoryImpl;
import mrizkisaputra.service.TodoListService;
import mrizkisaputra.service.TodoListServiceImpl;
import mrizkisaputra.view.TodoListView;

public class App {
    public static void main(String[] args) {
        TodoListRepository repository = new TodoListRepositoryImpl();
        TodoListService service = new TodoListServiceImpl(repository);
        TodoListView view = new TodoListView(repository, service);

        view.mainActivity();
    }
}
