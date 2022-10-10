package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }



    public void createUsersTable() {
        String myTable = "CREATE TABLE IF NOT EXISTS users(id INT  NOT NULL AUTO_INCREMENT, PRIMARY KEY (id), name VARCHAR(45) NOT NULL, lastname VARCHAR(45) NOT NULL, age INT NULL)";
        try (PreparedStatement ps = connection.prepareStatement(myTable)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error create");
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error drop table");
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users ( name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();

            System.out.println("");
        } catch (SQLException e) {
            System.out.println("Error save user");
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);

        } catch (SQLException e) {
            System.out.println("Error remove dy id");
            e.getErrorCode();
        }

    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        List<User> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error get all users");
            e.getErrorCode();
        }

        return list;
    }

    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE  users";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error clean table");
            e.getErrorCode();
        }

    }
}

