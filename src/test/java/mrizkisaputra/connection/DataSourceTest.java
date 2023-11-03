package mrizkisaputra.connection;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataSourceTest {

    private final Logger Log = LoggerFactory.getLogger(DataSourceTest.class);

    @Order(1)
    @Test
    @DisplayName("[TEST getConnection] : sebaiknya koneksi database berhasil")
    void getConnection() {
        try {
            HikariDataSource hikariDataSource = DataSource.connection();
            Connection connection = hikariDataSource.getConnection();

            connection.close();
            hikariDataSource.close();
            Log.info("success connect");
        } catch (SQLException error) {
            Log.error("failed connect", new SQLException(error.getMessage()));
        }
    }

    @Order(2)
    @Test
    @DisplayName("[TEST getConnection] : sebaiknya tidak mengembalikan exception")
    void getConnectionError() {
        Assertions.assertDoesNotThrow(() -> {
            HikariDataSource hikariDataSource = DataSource.connection();
            Connection connection = hikariDataSource.getConnection();
        });
    }
}
