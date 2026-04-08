package org.example.racekat_projekt2_2semester_thesemicolons.service;

import org.example.racekat_projekt2_2semester_thesemicolons.model.User;
import org.example.racekat_projekt2_2semester_thesemicolons.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return List.of();
    }//TODO

    public void createUser(User user) {

    }//TODO

    public void deleteUser(User user) {

    }//TODO

    public void editUser(User user) {

    }//TODO

    public void checkRole(User user) {

    }//TODO


    public User validateLogin(String email, String password){
        return null;
    }//TODO
}
