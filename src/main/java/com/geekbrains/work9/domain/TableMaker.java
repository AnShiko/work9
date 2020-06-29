package com.geekbrains.work9.domain;

import com.geekbrains.work9.annotations.Table;
import java.sql.Connection;
import java.util.List;

public class TableMaker {
    static public void makeTables(Connection connection, List <String> classList) {
        for (String person : classList) {
            try {
                Class tableClass = Class.forName(person);
                makeTable(connection, tableClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void makeTable(Connection connection, Class tableClass) {
        Table tableAnnotation = (Table) tableClass.getAnnotation(Table.class);
        if (tableAnnotation != null) {
            String tableName = tableAnnotation.name();
            if (tableName.equals("")) {
                tableName = tableClass.getSimpleName().toLowerCase();
            }
        }
    }
}

