package org.example;

import java.sql.Connection;

class TestDatabaseConnection extends DatabaseConnection {
    private final Connection connection;

    public TestDatabaseConnection(Connection connection) {
        super("jdbc:sqlite::memory:"); // dummy URL, won't be used
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}

