package org.example.racekat_projekt2_2semester_thesemicolons.service;
import org.example.racekat_projekt2_2semester_thesemicolons.model.Cat;
import org.example.racekat_projekt2_2semester_thesemicolons.model.User;
import org.example.racekat_projekt2_2semester_thesemicolons.repository.interfaces.ICatRepository;
import org.springframework.stereotype.Service;

@Service
public class CatService {

    private final ICatRepository catRepository;

    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void createCat(Cat cat, User user) throws IllegalArgumentException {
        cat.validateCatValues();
        assignPlaceholderImageIfNotPresent(cat);
        catRepository.addCat(cat, user);
    }

    public void deleteCat(int id){
        catRepository.deleteCat(id);
    }

    public void editCat(Cat cat) throws IllegalArgumentException {
        cat.validateCatValues();
        catRepository.editCat(cat);
    }

    public Cat findCatById(int id) {
        return catRepository.getCatById(id);
    }

    private void assignPlaceholderImageIfNotPresent(Cat cat) {
        if (cat.getImagePath().isEmpty() || cat.getImagePath() == null) {
            cat.setImagePath("kat.jpg");
        }
    }
}