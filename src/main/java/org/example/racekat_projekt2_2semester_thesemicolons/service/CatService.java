package org.example.racekat_projekt2_2semester_thesemicolons.service;
import org.example.racekat_projekt2_2semester_thesemicolons.model.Cat;
import org.example.racekat_projekt2_2semester_thesemicolons.model.User;
import org.example.racekat_projekt2_2semester_thesemicolons.repository.interfaces.ICatRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class CatService {

    private final ICatRepository catRepository;
    private final Path uploadPath = Paths.get("uploads");
    //private final Path uploadPath = Paths.get("src/main/resources/static/image/uploads");

    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void createCat(Cat cat, User user) throws IllegalArgumentException {
        cat.validateCatValues();
        assignPlaceholderImageIfNotPresent(cat);
        catRepository.addCat(cat, user);
    }

    public void createCatNew(Cat cat, MultipartFile image, User user) throws IllegalArgumentException, RuntimeException {
        cat.validateCatValues();

        if (image == null || image.isEmpty()) {
            assignPlaceholderImageIfNotPresent(cat);
        } else if (!Objects.requireNonNull(image.getOriginalFilename()).contains(".jpg")) {
            System.out.println(image.getOriginalFilename());
            throw new IllegalArgumentException("Forkert filtype: upload kun billeder i JPG eller PNG format");
        } else {
            assignUploadedPhotoToCat(cat, image);
        }

        catRepository.addCat(cat, user);
    }

    public void deleteCat(int id){
        catRepository.deleteCat(id);
    }

    public void editCat(Cat cat) throws IllegalArgumentException {
        cat.validateCatValues();
        catRepository.editCat(cat);
    }

    public void editCatNew(Cat cat, MultipartFile image) throws IllegalArgumentException {
        cat.validateCatValues();
        if (image != null || !image.isEmpty()) { //image will have a random value whether there was a file or not
            if (!image.getOriginalFilename().isEmpty()) { //checking if there was a file uploaded
                assignUploadedPhotoToCat(cat, image);
            }
        }
        catRepository.editCat(cat);
    }

    public Cat findCatById(int id) {
        return catRepository.getCatById(id);
    }

    private void assignPlaceholderImageIfNotPresent(Cat cat) {
        cat.setImagePath("kat.jpg");
    }

    private void assignUploadedPhotoToCat(Cat cat, MultipartFile image) {
        try {
            Files.createDirectories(uploadPath);

            String originalFilename = image.getOriginalFilename();
            String newFileName = UUID.randomUUID() + "_" + originalFilename;

            Path destination = uploadPath.resolve(newFileName);
            image.transferTo(destination);

            cat.setImagePath(newFileName);
        } catch (IOException e) {
            throw new RuntimeException("Fejl ved upload af billede", e);
        }
    }
}