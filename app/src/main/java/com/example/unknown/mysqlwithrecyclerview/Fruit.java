package com.example.unknown.mysqlwithrecyclerview;

/**
 * Created by UNKNOWN on 7/3/2016.
 */
public class Fruit {

    private int id;
    private String name;
    private String email;

    public Fruit() {

    }

    public Fruit (int id,String name,String email){
        this.setId(id);
        this.setEmail(email);
        this.setName(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
