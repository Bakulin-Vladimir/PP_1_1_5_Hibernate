package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    // реализуйте настройку соеденения с БД
    private static final String URL_FOR_JDBC = "jdbc:mysql://localhost:3306/my_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL_FOR_HIBERNATE = "jdbc:mysql://localhost:3306/my_db?useSSL=false";
    private static final String DIALECT = "org.hibernate.dialect.MySQL8Dialect";
    private static final String SHOW_SQL = "true";
    private static final String CURRENT_SESSION_CONTEXT_CLASS = "thread";
    private static final String HBM2DDL_AUTO = "create-drop";
    private static SessionFactory sessionFactory;

    public static Connection connectDatabase() {
        Connection connection;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL_FOR_JDBC, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static Session getSession() {

        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, DRIVER);
            settings.put(Environment.URL, URL_FOR_HIBERNATE);
            settings.put(Environment.USER, USER);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, DIALECT);
            settings.put(Environment.SHOW_SQL, SHOW_SQL);
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, CURRENT_SESSION_CONTEXT_CLASS);
            settings.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);
            sessionFactory = configuration.
                    setProperties(settings).
                    addAnnotatedClass(User.class).
                    buildSessionFactory();
        }

        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}
