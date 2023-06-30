package com.donjonthymleaf.donjonthymleaf;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Person {

    private int id;
    private String name;
    private PersonType type;
    private int heathPoint;

    public Person() {

    }

    public Person(String name, PersonType type) {
        this.name = name;
        this.type = type;
    }

    public Person(int id, String name, PersonType type) {
        this(name, type);
        this.id = id;
        this.heathPoint = 10;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    public int getHeathPoint() {
        return heathPoint;
    }

    public void setHeathPoint(int heathPoint) {
        this.heathPoint = heathPoint;
    }


}
