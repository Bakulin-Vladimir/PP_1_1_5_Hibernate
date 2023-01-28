package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl(new UserDaoJDBCImpl());
        userService.createUsersTable();
        userService.saveUser("Петр", "Иванов", (byte) 34);
        userService.saveUser("Мария", "Петрова", (byte) 25);
        userService.saveUser("Виктор", "Васильев", (byte) 29);
        userService.saveUser("Елена", "Иванова", (byte) 30);
        List<User> allUsers = userService.getAllUsers();
        allUsers.stream().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
