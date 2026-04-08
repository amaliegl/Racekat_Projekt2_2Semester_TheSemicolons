package org.example.racekat_projekt2_2semester_thesemicolons.repository;

import org.example.racekat_projekt2_2semester_thesemicolons.model.Cat;
import org.example.racekat_projekt2_2semester_thesemicolons.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserRepositoryMySql implements IUserRepository{

    @Override
    public List<User> findAllUsers() {
        return List.of();
    }//TODO

    @Override
    public User createUser() {
        return null;
    }//TODO

    @Override
    public void deleteUser(User user) {

    }//TODO

    @Override
    public void editUser(User user) {

    }//TODO

    @Override
    public User validateLogin(String email, String password) {
        return null;
    }//TODO

    @Override
    public void addCatToUser(Cat cat) {

    }//TODO
}
