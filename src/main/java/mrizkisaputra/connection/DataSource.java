package mrizkisaputra.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static final HikariConfig hikariConfig = new HikariConfig("datasource.properties");
    private static final HikariDataSource hikariDataSource;

    static {
        hikariConfig.setMaximumPoolSize(100);
        hikariConfig.setMinimumIdle(50);
        hikariConfig.setIdleTimeout(60_000);
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    public static HikariDataSource connection() throws SQLException {
        return hikariDataSource;
    }
}
