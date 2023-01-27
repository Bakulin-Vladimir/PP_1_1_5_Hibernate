package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Петр", "Иванов", (byte) 34);
        userDaoJDBC.saveUser("Мария", "Петрова", (byte) 25);
        userDaoJDBC.saveUser("Виктор", "Васильев", (byte) 29);
        userDaoJDBC.saveUser("Елена", "Иванова", (byte) 30);
        List<User> allUsers = userDaoJDBC.getAllUsers();
        allUsers.stream().forEach(System.out::println);
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();

    }
}
