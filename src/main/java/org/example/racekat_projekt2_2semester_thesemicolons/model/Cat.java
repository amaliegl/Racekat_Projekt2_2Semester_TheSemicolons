package org.example.racekat_projekt2_2semester_thesemicolons.model;

import java.time.LocalDate;
import java.time.Period;

public class Cat {
    private int id; //final
    private int ownerId;
    private String name;
    private LocalDate birthday; //final
    private Sex_ENUM sex; //final
    private boolean fertile;
    private boolean alive;
    private String imagePath;
    private String pedigreePath;


    public Cat(int id, LocalDate birthday, Sex_ENUM sex) {
        this.id = id;
        this.birthday = birthday;
        this.sex = sex;
    }

    //public Cat() {}

    public int getId() { return this.id;}
    public void setId(int id) { this.id = id;}

    public int getOwnerId() { return ownerId;}
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public Sex_ENUM getSex() { return this.sex; }
    public void setSex(Sex_ENUM sex) {this.sex = sex; }

    public boolean isFertile() { return fertile; }
    public void setFertile(boolean fertile) { this.fertile = fertile; }

    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getPedigreePath() { return pedigreePath; }
    public void setPedigreePath(String pedigreePath) { this.pedigreePath = pedigreePath; }

    public String getAge() {
        Period age = Period.between(birthday, LocalDate.now());

        if (age.getYears() < 1) {
            return age.getMonths() + " months";
        }
        return age.getYears() + " years";
    }
}