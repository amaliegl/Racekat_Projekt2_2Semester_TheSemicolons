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

    public void createCat(Cat cat, User user){
        System.out.println("Katten, da den kommer ind i create: " + cat);
        assignPlaceholderImageIfNotPresent(cat);
        System.out.println("Katten, da den kommer UD " + cat);
        catRepository.addCat(cat, user);
    }//TODO - slet sout

    public void deleteCat(int id){
        catRepository.deleteCat(id);
    }//TODO

    public void editCat(Cat cat){
        catRepository.editCat(cat);
    }//TODO

    public Cat findCatById(int id) {
        return catRepository.getCatById(id);
    }

    private Cat assignPlaceholderImageIfNotPresent(Cat cat) {
        if (cat.getImagePath().isEmpty() || cat.getImagePath() == null) {
            cat.setImagePath("kat.jpg");
        }
        return cat;
    }
}
