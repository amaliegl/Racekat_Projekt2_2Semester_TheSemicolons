package org.example.racekat_projekt2_2semester_thesemicolons.service;

import org.example.racekat_projekt2_2semester_thesemicolons.model.User;
import org.example.racekat_projekt2_2semester_thesemicolons.repository.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
        //return List.of();
    }//TODO

    public User createUser(User user) {
        /*if (userRepository.findByEmail(user.getEmail()) != null) {
            return false; // Brugernavnet findes allerede
        }

        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);*/

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
        /*User user = userRepository.findByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }*/
        return null;
    }//TODO

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
