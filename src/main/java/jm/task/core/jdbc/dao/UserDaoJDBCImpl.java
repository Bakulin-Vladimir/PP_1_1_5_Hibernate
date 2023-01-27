package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String CREATE_TABLE = "create table if not exists User (id INT primary key auto_increment,\n" +
            "name VARCHAR(25) not null,\n" +
            "lastName VARCHAR(25) not null,\n" +
            "age tinyint not null)";
    private static final String DROP_TABLE = "drop table if exists User";
    private static final String INSERT_TABLE = "insert into User(name,lastName,age) values(?,?,?)";
    private static final String DELETE_USER_ID = "delete from user where id=?";
    private static final String SELECT_USER = "select * from user";
    private static final String CLEAN_TABLE = "truncate table user";
    Util util = new Util();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            util.connectDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            util.connectDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            util.connectDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = util.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_TABLE)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User с именем" + " – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        try {
            util.connectDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = util.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try {
            util.connectDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        List<User> listUser = new ArrayList<>();
        try (Connection connection = util.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                listUser.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUser;
    }

    public void cleanUsersTable() {
        try {
            util.connectDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CLEAN_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
