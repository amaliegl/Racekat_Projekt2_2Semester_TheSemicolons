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


    public User validateLogin(String email, String password) {
        return null;
    }//TODO
}
