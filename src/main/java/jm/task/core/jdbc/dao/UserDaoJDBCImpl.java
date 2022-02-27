package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.getConnect();
             Statement st = conn.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT," +
                    " name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL," +
                    " age TINYINT, PRIMARY KEY (id))");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();


        }


    }


    public void dropUsersTable() {
        try (Connection conn = Util.getConnect();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {

        try (Connection conn = Util.getConnect();
             PreparedStatement pr = conn.prepareStatement("INSERT INTO  users(name, lastName,age) VALUES(?,?,?)")) {
            pr.setString(1, name);
            pr.setString(2, lastName);
            pr.setByte(3, age);
            pr.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных", name);
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnect();
             PreparedStatement pr = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pr.setLong(1, id);
            pr.executeUpdate();
            System.out.println("Пользователь удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection conn = Util.getConnect();
             Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("LastName");
                Byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);
                System.out.println("БД получена");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public void cleanUsersTable() {
        try (Connection conn = Util.getConnect();
             PreparedStatement ps  = conn.prepareStatement("DELETE  FROM  users")) {
            ps.executeUpdate();
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



