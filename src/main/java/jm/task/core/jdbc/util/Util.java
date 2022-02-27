package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Rammstein25";
    private static final String URL = "jdbc:mysql://localhost:3306/base";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnect() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Подключение к БД прошло успешно");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к БД");

        } return connection;
    }

}
