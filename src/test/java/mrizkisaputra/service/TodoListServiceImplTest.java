package mrizkisaputra.service;

import mrizkisaputra.entity.TodoList;
import mrizkisaputra.repository.TodoListRepository;
import mrizkisaputra.repository.TodoListRepositoryImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TodoListServiceImplTest {
    @Mock
    private TodoListRepository repository;
    private TodoListService service;

    @BeforeEach
    void setUp() {
        service = new TodoListServiceImpl(repository);
    }

    @Test
    @Order(1)
    @DisplayName("[TEST insert] sebaiknya insert data berhasil")
    void insert() {
        TodoList todo1 = new TodoList("Todo 1");
        service.insert(todo1);
        Mockito.verify(repository, Mockito.times(1)).insert(todo1);
    }

    @Test
    @Order(2)
    @DisplayName("[TEST displayTodoList] sebaiknya berhasil menampilkan todolist")
    void displayTodolist() {
        ArrayList<TodoList> listTodo = new ArrayList<>();
        listTodo.add(new TodoList(1, "Todo 1", LocalDateTime.now()));
        listTodo.add(new TodoList(2, "Todo 2", LocalDateTime.now()));

        // menambahkan behavior di method milik mocking objek
        Mockito.when(repository.getAll())
                        .thenReturn(listTodo);
        service.displayTodolist();

        // memverifikasi untuk memastikan method getAll dari mocking objek setidaknya dipanggil 1x
        Mockito.verify(repository, Mockito.times(1))
                .getAll();
    }

    @Test
    @Order(3)
    @DisplayName("[TEST update] sebaiknya mengembalikan boolean true")
    void update() {
        // Persiapkan data palsu
        Integer todoId = 1;
        String newTodo = "Updated Todo";

        // Atur perilaku objek mock
        Mockito.when(repository.update(todoId, newTodo)).thenReturn(true);

        // Panggil metode yang ingin diuji
        boolean result = service.update(todoId, newTodo);

        // Verifikasi bahwa metode update pada repository dipanggil dengan argumen yang benar
        Mockito.verify(repository, Mockito.times(1)).update(todoId, newTodo);

        // Verifikasi bahwa metode update mengembalikan nilai true (berhasil)
        assertTrue(result);
    }

    @Test
    @Order(4)
    @DisplayName("[TEST delete] sebaiknya mengembalikan boolean true")
    void delete() {
        Integer todoId = 1;
        Mockito.when(repository.delete(todoId)).thenReturn(true);
        boolean result = service.delete(todoId);
        assertTrue(result);

        Mockito.verify(repository, Mockito.times(1)).delete(todoId);
    }
}