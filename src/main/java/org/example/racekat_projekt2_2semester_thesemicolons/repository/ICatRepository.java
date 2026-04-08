package org.example.racekat_projekt2_2semester_thesemicolons.repository;

import org.example.racekat_projekt2_2semester_thesemicolons.model.Cat;

public interface ICatRepository {
    public void addCat(Cat cat);
    public void deleteCat(Cat cat);
    public void editCat(Cat cat);
}
