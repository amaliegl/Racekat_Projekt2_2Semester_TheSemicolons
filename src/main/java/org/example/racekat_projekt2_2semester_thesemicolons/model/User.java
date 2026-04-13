package org.example.racekat_projekt2_2semester_thesemicolons.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Role_ENUM role;
    private String phone;
    private List<Cat> cats = new ArrayList<>();

    public User(int id, String name, String email, Role_ENUM role, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.phone = phone;
    }

    //For testing
    public User(String name, String email, String password, Role_ENUM role, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
    }

    public User(int id, String name, String email, String password, Role_ENUM role, String phone, List<Cat> cats) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.cats = cats;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public Role_ENUM getRole() {return role;}
    public void setRole(Role_ENUM role) {this.role = role;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public List<Cat> getCats() {return cats;}
    public void setCats(List<Cat> cats) {this.cats = cats;}

    public void addCatToCats(Cat cat) {
        this.cats.add(cat);
    }

    @Override
    public String toString() {
        return getId() + " " + name + " " + cats;
    }
}


