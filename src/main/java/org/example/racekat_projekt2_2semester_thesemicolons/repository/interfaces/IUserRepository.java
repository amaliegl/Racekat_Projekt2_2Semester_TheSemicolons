package org.example.racekat_projekt2_2semester_thesemicolons.repository.interfaces;

import org.example.racekat_projekt2_2semester_thesemicolons.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserRepository {

    public List<User> findAllUsers();

    public User createUser(User user);

    public void deleteUser(User user);

    public void editUser(User user);

    public User validateLogin(String email, String password);
}
