package com.geekbrains.work9.repos;

import com.geekbrains.work9.annotations.Column;
import com.geekbrains.work9.annotations.Table;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableRepo {

    public void save(Connection connection, Object object) {
        Class objectClass = object.getClass();
        Table tableAnnotation = (Table) objectClass.getAnnotation(Table.class);
        if (tableAnnotation != null) {
            String tableName = tableAnnotation.name();
            if (tableName.equals("")) {
                tableName = objectClass.getSimpleName().toLowerCase();
            }

            Field[] fields = objectClass.getDeclaredFields();
            Map <String, Object> columns = new LinkedHashMap <>();

            for (Field field : fields) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation != null) {
                    String columnName = columnAnnotation.name();
                    if (columnName.equals("")) {
                        columnName = field.getName().toLowerCase();
                    }

                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    Object value = null;
                    try {
                        value = field.get(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    columns.put(columnName, value);
                }
            }
        }
    }
}

