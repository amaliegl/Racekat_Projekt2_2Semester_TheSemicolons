package org.example.racekat_projekt2_2semester_thesemicolons.repository;

import org.example.racekat_projekt2_2semester_thesemicolons.model.Cat;
import org.example.racekat_projekt2_2semester_thesemicolons.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserRepository {

    public List<User> findAllUsers();

    public User createUser();

    public void deleteUser(User user);

    public void editUser(User user);

    public User validateLogin(String email, String password);

    public void addCatToUser(Cat cat);
}
