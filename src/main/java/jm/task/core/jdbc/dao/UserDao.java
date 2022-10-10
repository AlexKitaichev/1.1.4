package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable();  //создать таблицу

    void dropUsersTable();   // сбросить в таблицу

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);  //удалить

    List<User> getAllUsers();        //читать

    void cleanUsersTable();
}
