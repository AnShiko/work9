package com.geekbrains.work9.domain;

import com.geekbrains.work9.annotations.Column;
import com.geekbrains.work9.annotations.Id;
import com.geekbrains.work9.annotations.Table;

@Table(name = "persons")
public class Person {

    @Id
    @Column(name = "persons_id")
    private long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private int rate;


    public Person(long id, String name, int age, int rate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.rate = rate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}





