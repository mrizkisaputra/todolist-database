package mrizkisaputra.repository;

import com.zaxxer.hikari.HikariDataSource;
import lombok.NonNull;
import mrizkisaputra.connection.DataSource;
import mrizkisaputra.entity.TodoList;
import mrizkisaputra.sql.TodoListSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

public class TodoListRepositoryImpl implements TodoListRepository {
    private final Logger Log = LoggerFactory.getLogger(TodoListRepositoryImpl.class);
    private HikariDataSource connect() {
        HikariDataSource hikariDataSource = null;
        try {
            hikariDataSource = DataSource.connection();
            Log.info("connection success");
        } catch (SQLException error) {
            Log.error(error.getMessage());
        }
        return hikariDataSource;
    }

    private TodoListRepositoryImpl closeConnection(@NonNull AutoCloseable autoCloseable) {
        try {
            autoCloseable.close();
        } catch (Exception error) {
            Log.error(error.getMessage());
        }
        return this;
    }

    private Boolean isExists(Integer todoId) {
        HikariDataSource hikariDataSource = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            hikariDataSource = connect();
            conn = hikariDataSource.getConnection();
            pstmt = conn.prepareStatement(TodoListSQL.SELECT_BY_TODO_ID.getQuery());
            pstmt.setInt(1, todoId);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) return true;
        } catch (SQLException error) {
            Log.error(error.getMessage(), new SQLException());
        } finally {
            if (conn != null) {
                this.closeConnection(conn)
                        .closeConnection(pstmt);
            }
        }
        return false;
    }

    @Override
    public void insert(TodoList todoList) {
        HikariDataSource hikariDataSource = null;
        Connection conn = null;
        PreparedStatement pstmtInsertTodo = null;
        try {
            hikariDataSource = connect();
            conn = hikariDataSource.getConnection();
            pstmtInsertTodo = conn.prepareStatement(TodoListSQL.INSERT_TODO.getQuery());
            pstmtInsertTodo.setString(1, todoList.getTodo());

            int affectedRows = pstmtInsertTodo.executeUpdate();
            if (affectedRows <= 0) {
                Log.error("insert todo failed affected rows ("+affectedRows+")", new SQLException());
            } else {
                Log.info("inserted success affected rows ({})", affectedRows);
            }
        } catch (Exception error) {
            Log.error("{}", error.getMessage());
        } finally {
            if (conn != null) {
                this.closeConnection(conn)
                        .closeConnection(pstmtInsertTodo);
            }
        }
    }

    @Override
    public ArrayList<TodoList> getAll() {
        HikariDataSource hikariDataSource = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        ArrayList<TodoList> listTodo = new ArrayList<>();

        try {
            hikariDataSource = connect();
            conn = hikariDataSource.getConnection();
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(TodoListSQL.SELECT_TODO.getQuery());
            while (resultSet.next()) {
                TodoList todoList = new TodoList(
                        resultSet.getInt("todo_id"),
                        resultSet.getString("todo"),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                );
                listTodo.add(todoList);
            }
            if (!resultSet.next()) return listTodo;
        } catch (SQLException error) {
            Log.error(error.getMessage(), new SQLException());
        } finally {
            if (conn != null) {
                this.closeConnection(conn)
                        .closeConnection(stmt)
                        .closeConnection(resultSet);
            }
        }
        return listTodo;
    }

    @Override
    public Boolean update(Integer todoId, String newTodo) {
        HikariDataSource hikariDataSource = null;
        Connection conn = null;
        PreparedStatement pstmtUpdateTodo = null;
        if (isExists(todoId)) {
            try {
                hikariDataSource = connect();
                conn = hikariDataSource.getConnection();
                pstmtUpdateTodo = conn.prepareStatement(TodoListSQL.UPDATE_TODO.getQuery());
                pstmtUpdateTodo.setString(1, newTodo);
                pstmtUpdateTodo.setInt(2, todoId);
                int affectedRows = pstmtUpdateTodo.executeUpdate();
                if (affectedRows > 0) return true;
                Log.error("update todo failed affected rows ({})", affectedRows);
            } catch (SQLException error) {
                Log.error(error.getMessage(), new SQLException());
            } finally {
                if (conn != null) {
                    this.closeConnection(conn)
                            .closeConnection(pstmtUpdateTodo);
                }
            }
        }
        return false;
    }

    @Override
    public Boolean delete(Integer todoId) {
        HikariDataSource hikariDataSource = null;
        Connection conn = null;
        PreparedStatement pstmtDeleteTodo = null;
        if (isExists(todoId)) {
            try {
                hikariDataSource = connect();
                conn = hikariDataSource.getConnection();
                pstmtDeleteTodo = conn.prepareStatement(TodoListSQL.DELETE_TODO.getQuery());
                pstmtDeleteTodo.setInt(1, todoId);
                int affectedRows = pstmtDeleteTodo.executeUpdate();
                if (affectedRows > 0) return true;
                Log.error("dlete todo failed affected rows ({})", affectedRows);
            } catch (SQLException error) {
                Log.error(error.getMessage(), new SQLException());
            } finally {
                if (conn != null) {
                    this.closeConnection(conn)
                            .closeConnection(pstmtDeleteTodo);
                }
            }
        }
        return false;
    }
}
