package org.example.racekat_projekt2_2semester_thesemicolons.service;
import org.example.racekat_projekt2_2semester_thesemicolons.model.Cat;
import org.example.racekat_projekt2_2semester_thesemicolons.repository.ICatRepository;
import org.springframework.stereotype.Service;

@Service
public class CatService {

    private final ICatRepository catRepository;

    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void createCat(Cat cat){

    }//TODO

    public void deleteCat(Cat cat){

    }//TODO

    public void editCat(Cat cat){

    }//TODO
}
