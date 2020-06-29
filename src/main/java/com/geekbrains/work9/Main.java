package com.geekbrains.work9;

import com.geekbrains.work9.domain.Person;
import com.geekbrains.work9.domain.TableMaker;
import com.geekbrains.work9.repos.TableRepo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final boolean AUTO_MAKE_TABLES = true;
    private static final String PAKKAGE
            = "com.geekbrains.work9.tables.";

    private static Connection connection;

    public static void main(String[] args) {
        connect();

        if (AUTO_MAKE_TABLES) {
            List<String> classList = new ArrayList<>(Arrays.asList(
                    PAKKAGE + "Person"));
            TableMaker.makeTables(connection, classList);
        }

        TableRepo repository = new TableRepo();

        Person person = new Person(123, "Andrey", 29,300);

        repository.save(connection, person);

        disconnect();
    }

    private static void connect() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String url = "jdbc:h2://localhost:9092/work9";
            String username = "sa";
            String pass = "";


            connection = DriverManager.getConnection(url, username, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

