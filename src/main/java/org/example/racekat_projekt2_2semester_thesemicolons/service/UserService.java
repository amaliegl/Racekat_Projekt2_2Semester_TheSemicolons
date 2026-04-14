package org.example.racekat_projekt2_2semester_thesemicolons.service;

import org.example.racekat_projekt2_2semester_thesemicolons.model.User;
import org.example.racekat_projekt2_2semester_thesemicolons.repository.interfaces.IUserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository userRepository;

    //TODO - spørg Mikkel om den røde streg
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
        //return List.of();
    }//TODO

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null; // Brugernavnet findes allerede
        }

        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);

        return userRepository.createUser(user);
    }//TODO

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }//TODO

    public void editUser(User user) {
        userRepository.editUser(user);
    }//TODO

    public void checkRole(User user) {
        //til at tjekke, om bruger må få adgang til ting (brug bare direkte fra brugerobjektet)
    }//TODO


    public User login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return null;
        }
        User loggedInUser = user.get();
        if (BCrypt.checkpw(password, loggedInUser.getPassword())) {
            return loggedInUser;
        }
        return null;
    }//TODO

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
