package org.example.racekat_projekt2_2semester_thesemicolons;

import org.example.racekat_projekt2_2semester_thesemicolons.model.*;
import org.example.racekat_projekt2_2semester_thesemicolons.repository.CatRepository;
import org.example.racekat_projekt2_2semester_thesemicolons.repository.UserRepositoryMySql;
import org.example.racekat_projekt2_2semester_thesemicolons.service.CatService;
import org.example.racekat_projekt2_2semester_thesemicolons.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

@SpringBootApplication
public class RacekatProjekt22SemesterTheSemicolonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RacekatProjekt22SemesterTheSemicolonsApplication.class, args);

        /*JdbcTemplate jdbc = new JdbcTemplate();
        UserRepositoryMySql userRepo = new UserRepositoryMySql(jdbc);
        UserService userService = new UserService(userRepo);
        CatRepository catRepo = new CatRepository(jdbc);
        CatService catService = new CatService(catRepo);

        User user = new User("Jytte", "jytte@kat.dk", "1234", Role_ENUM.Member, "87654321");
        Cat cat = new Cat("Mille", LocalDate.now(), Color_ENUM.Lilac, Sex_ENUM.Female, false, true, "placeholder.jpg", "placeholder.jpg");

        User savedUser = userService.createUser(user);
        System.out.println("Bruger gemt i database: " + savedUser);
        catService.createCat(cat, user);


        System.out.println("Alle brugere fra databasen: " + userService.getAllUsers());
        System.out.println("Jyttes liste af katte: " + user.getCats());*/





    }

}
