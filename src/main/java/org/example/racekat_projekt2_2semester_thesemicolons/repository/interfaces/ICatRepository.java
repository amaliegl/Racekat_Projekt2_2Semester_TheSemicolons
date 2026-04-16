package org.example.racekat_projekt2_2semester_thesemicolons.repository.interfaces;

import org.example.racekat_projekt2_2semester_thesemicolons.model.Cat;
import org.example.racekat_projekt2_2semester_thesemicolons.model.User;

public interface ICatRepository {
    public void addCat(Cat cat, User user);
    public void deleteCat(int id);
    public void editCat(Cat cat);
    public Cat getCatById(int id);
}
