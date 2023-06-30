package com.donjonthymleaf.donjonthymleaf;

public class PersonForm {

    private int id;
    private String name;
    private PersonType type;
    private int heathPoint;

    public PersonForm(String name, String type) {
        this.name = name;
        this.type = PersonType.valueOf(type);
    }

    public PersonForm() {

    }

    public PersonForm(String name, PersonType type) {
        this.name = name;
        this.type = type;
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
