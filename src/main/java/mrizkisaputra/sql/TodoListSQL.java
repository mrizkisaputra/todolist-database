package mrizkisaputra.sql;

import lombok.Getter;

@Getter
public enum TodoListSQL {
    INSERT_TODO("INSERT INTO todolist(todo) VALUES(?);"),
    DELETE_TODO("DELETE FROM todolist WHERE todo_id = ?;"),
    UPDATE_TODO("UPDATE todolist SET todo = ? WHERE todo_id = ?;"),
    SELECT_TODO("SELECT * FROM todolist;"),
    SELECT_BY_TODO_ID("SELECT * FROM todolist WHERE todo_id = ?;");

    private final String query;
    TodoListSQL(String query) {
        this.query = query;
    }
}
