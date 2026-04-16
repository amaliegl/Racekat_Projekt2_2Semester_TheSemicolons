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

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public User createUser(User user) throws IllegalArgumentException {
        user.validateUserValues();
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null; // Email is already in use
        }

        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);

        return userRepository.createUser(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public User editUser(User newUser, User oldUser) throws IllegalArgumentException {
        User updatedUser = updateIdOnEditedUser(newUser, oldUser);
        updatedUser.validateUserValues();
        userRepository.editUserFromUserEditForm(updatedUser);
        return userRepository.findByExistingId(updatedUser.getId()); //a part of Optional
    }

    private User updateIdOnEditedUser(User newUser, User oldUser){
        newUser.setId(oldUser.getId());
        return newUser;
    }

    public void checkRole(User user) {
        //til at tjekke, om bruger må få adgang til ting (brug bare direkte fra brugerobjektet)
    }


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
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByExistingId(int id) {
        return userRepository.findByExistingId(id);
    }
}
